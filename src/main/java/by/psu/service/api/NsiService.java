package by.psu.service.api;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.Nsi;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.repository.RepositoryNsi;
import by.psu.service.merger.AbstractNsiMerger;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public abstract class NsiService<T extends Nsi> {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected RepositoryNsi<T> repositoryNsi;

    @Autowired
    private AbstractNsiMerger<T> abstractNsiMerger;

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> type;


    public NsiService() {
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public List<T> getAll() {
        return repositoryNsi.findAll();
    }

    public T getOne(UUID uuid) {
        return repositoryNsi.getOne(uuid);
    }

    @Transactional
    protected Optional<T> isExists(T nsi) {

        if (nsi == null) {
            return Optional.empty();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);

        Subquery<StringValue> stringValueSubquery = criteriaQuery.subquery(StringValue.class);
        Root<StringValue> stringValueRoot = stringValueSubquery.from(StringValue.class);

        Optional<StringValue> stringValueEn = TranslateUtil.getValueByLanguage(nsi.getTitle(), Language.EN);
        Optional<StringValue> stringValueRu = TranslateUtil.getValueByLanguage(nsi.getTitle(), Language.RU);

        List<Predicate> expressions = new ArrayList<>();

        stringValueEn.ifPresent(stringValue -> expressions.add(
                criteriaBuilder.like(criteriaBuilder.lower(stringValueRoot.get("value")), stringValue.getValue().toLowerCase())
        ));

        stringValueRu.ifPresent(stringValue -> expressions.add(
                criteriaBuilder.like(criteriaBuilder.lower(stringValueRoot.get("value")), stringValue.getValue().toLowerCase())
        ));

        if (expressions.isEmpty()) {
            throw new RuntimeException("Nsi doesn't have a valid value");
        }

        Predicate predicate = expressions.size() == 1 ? expressions.get(0) :
                    criteriaBuilder.or(expressions.toArray(new Predicate[0]));

        stringValueSubquery.select(stringValueRoot)
                .where(
                        criteriaBuilder.and(
                                predicate,
                                criteriaBuilder.equal(root.get("title"), stringValueRoot.get("translate"))
                        )
                );

        criteriaQuery.select(root).where(criteriaBuilder.exists(stringValueSubquery));
        TypedQuery<T> tTypedQuery = entityManager.createQuery(criteriaQuery);

        List<T> objList = tTypedQuery.getResultList();
        return objList.stream().findFirst();
    }

    @Transactional
    public T update(T nsi) {
        Optional<T> optionalNsi = Optional.of(nsi);
        optionalNsi.orElseThrow(() -> new RuntimeException("Nsi is null", new BadHttpRequest()));
        optionalNsi.map(Nsi::getId).orElseThrow(() -> new RuntimeException("Id is null"));

        Optional<T> findNsi = Optional.of(repositoryNsi.getOne(nsi.getId()));
        findNsi.orElseThrow(() -> new RuntimeException(new EntityNotFoundException("Nsi not found")));

        optionalNsi.map(Nsi::getTitle).orElseThrow(() -> new RuntimeException(new EntityNotFoundException("Nsi title is null")));
        findNsi.map(Nsi::getTitle).orElseThrow(() -> new RuntimeException(new EntityNotFoundException("Find nsi (BD) title is null")));

        return repositoryNsi.save(abstractNsiMerger.merge(findNsi.get(), nsi));
    }

    @Transactional
    public T save(T nsi) {
        Optional<T> optionalNsi = Optional.ofNullable(nsi);
        optionalNsi.orElseThrow(() -> new RuntimeException("Nsi is null", new BadHttpRequest()));

        if ( optionalNsi.map(Nsi::getId).isPresent() ) {
             return repositoryNsi.getOne(optionalNsi.map(Nsi::getId).get());
        }

        optionalNsi.map(Nsi::getTitle).orElseThrow(() -> new RuntimeException("Nsi title is null", new BadHttpRequest()));

        optionalNsi.get().getTitle().setListValues(optionalNsi.get().getTitle().getValues());

        return isExists(optionalNsi.get()).orElseGet(() -> repositoryNsi.save(optionalNsi.get()));
    }

    public List<T> place(List<T> nsiCollection) {

        if (isNull(nsiCollection)) {
            return new ArrayList<>();
        }

        return nsiCollection.stream()
                .map(nsiItem -> isExists(nsiItem).orElseGet(() -> repositoryNsi.save(nsiItem)))
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(UUID uuid) {
        repositoryNsi.deleteById(uuid);
    }

    @Transactional
    public void deleteAll(List<UUID> uuid, final Consumer<T> consumer) {
        uuid.stream()
                .map(repositoryNsi::findById)
                .map(Optional::get)
                .forEach(nsi -> {
                    consumer.accept(nsi);

                    entityManager.merge(nsi);
                    entityManager.flush();
                    entityManager.remove(nsi);
                });
    }
}
