package by.psu.service.api2;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.Nsi;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.repository.RepositoryNsi;
import javassist.NotFoundException;
import javassist.tools.web.BadHttpRequest;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

abstract public class ServiceNsi<T extends Nsi> {

    @Autowired
    protected RepositoryNsi<T> repositoryNsi;

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> type = null;


    public ServiceNsi(Class<T> classT) {
        this.type = classT;
    }


    public List<T> getAll() {
        return repositoryNsi.findAll();
    }

    public T getOne(UUID uuid) {
        return repositoryNsi.getOne(uuid);
    }

    protected Optional<T> isExists(T nsi) {
        if (nsi == null) {
            return Optional.empty();
        }

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> root = criteriaQuery.from(type);

        Subquery<StringValue> stringValueSubquery = criteriaQuery.subquery(StringValue.class);
        Root<StringValue> stringValueRoot = stringValueSubquery.from(StringValue.class);

        Optional<StringValue> stringValueEn = LanguageUtil.getValueByLanguage(nsi.getTitle(), Language.EN);
        Optional<StringValue> stringValueRu = LanguageUtil.getValueByLanguage(nsi.getTitle(), Language.RU);

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

        Predicate predicate = expressions.size() == 1 ?
                    expressions.get(0) :
                    criteriaBuilder.or(expressions.toArray(new Predicate[0]));

        stringValueSubquery.select(stringValueRoot)
                .where(
                        criteriaBuilder.and(
                                predicate,
                                criteriaBuilder.equal(root.get("title"), stringValueRoot.get("translate"))
                        )
                );

        criteriaQuery.select(root)
                .where(
                            criteriaBuilder.exists(stringValueSubquery)
                );

        TypedQuery<T> tTypedQuery = entityManager.createQuery(criteriaQuery);
        return Optional.of(tTypedQuery.getSingleResult());
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_READ)
    public T update(T nsi) {
        Optional.of(nsi).orElseThrow(() -> new RuntimeException("Nsi is null", new BadHttpRequest()));
        Optional.of(nsi.getId()).orElseThrow(() -> new RuntimeException("Id is null"));

        Optional<T> findNsi = Optional.of(repositoryNsi.getOne(nsi.getId()));
        findNsi.orElseThrow(() -> new RuntimeException(new EntityNotFoundException("Nsi not found")));
        findNsi.get().setTitle(nsi.getTitle());

        return findNsi.get();
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public T save(T nsi) {
        if ( nsi == null ) {
            throw new RuntimeException("Nsi is null", new BadHttpRequest());
        }

        if ( nsi.getId() != null ) {
            throw new RuntimeException(new EntityExistsException("Id is not null"));
        }

        if( nsi.getTitle() == null ) {
            throw new RuntimeException("Nsi title is null", new BadHttpRequest());
        }

        return repositoryNsi.save(nsi);
    }

    public List<T> place(List<T> nsiCollection) {

        if (isNull(nsiCollection)) {
            return new ArrayList<>();
        }

        return nsiCollection.stream()
                .map(nsiItem -> isExists(nsiItem).orElseGet(() -> repositoryNsi.save(nsiItem)))
                .collect(Collectors.toList());
    }
}
