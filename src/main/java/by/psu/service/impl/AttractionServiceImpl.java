package by.psu.service.impl;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.exceptions.SearchRequestException;
import by.psu.exceptions.ServerDataBaseException;
import by.psu.model.Attraction;
import by.psu.model.Tag;
import by.psu.model.TypeAttraction;
import by.psu.repository.AttractionRepository;
import by.psu.repository.TypeAttractionRepository;
import by.psu.service.AttractionService;
import by.psu.service.TagService;
import by.psu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

@Service
public class AttractionServiceImpl implements AttractionService {

    private final EntityManager entityManager;
    private final AttractionRepository repository;
    private final TypeAttractionRepository typeAttractionRepository;

    private final TypeService typeService;
    private final TagService tagService;

    @Autowired
    public AttractionServiceImpl(AttractionRepository repository, EntityManager entityManager, TypeAttractionRepository typeAttractionRepository, TypeService typeService, TagService tagService) {
        this.repository = repository;
        this.entityManager = entityManager;
        this.typeAttractionRepository = typeAttractionRepository;
        this.typeService = typeService;
        this.tagService = tagService;
    }

    @Override
    public List<Attraction> findAll() {
        return repository.findAllByOrderByTitleAttractionAsc();
    }


    @Override
    public Attraction findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Attraction save(Attraction obj) {
        return Optional.of(repository.save(obj)).orElseThrow(ServerDataBaseException::new);
    }

    @Override
    public Attraction update(Attraction obj, Long id) {
        repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        obj.setId(id);
        return Optional.of(repository.save(obj))
                .orElseThrow(ServerDataBaseException::new);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        Attraction attraction = repository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        try {
            repository.delete(attraction);
        } catch (RuntimeException ex) {
            throw new ServerDataBaseException();
        }
    }

    @Override
    public ArrayList findAllFilter(Long[] typeAttractions, Integer sortField, Integer direction) {
        List<TypeAttraction> list;
        StringBuilder buildTypeAttractionSql = new StringBuilder();
        if (typeAttractions.length > 0) {
            buildTypeAttractionSql.append("JOIN attr.types u WHERE ");
            buildTypeAttractionSql.append("(");
            for (int i = 0; i < typeAttractions.length; i++) {
                TypeAttraction typeAttraction = typeAttractionRepository.findById(typeAttractions[i]).orElse(null);
                if (Objects.nonNull(typeAttraction)) {
                    buildTypeAttractionSql.append(String.format("u.id = %s", typeAttraction.getId().toString()));
                    buildTypeAttractionSql.append(i != typeAttractions.length - 1 ? " or " : ")");
                }
            }
        }

        String req = "select distinct attr " +
                "from Attraction attr "
                + buildTypeAttractionSql + " " +
                "ORDER BY " + selectSort(sortField) + " " + ((direction == 0) ? "asc" : "desc");

        Query query = entityManager.createQuery(req);

        return new ArrayList<Attraction>(query.getResultList());
    }

    private String selectSort(Integer index) {
        switch (index) {
            case 1:
                return "attr.pickupServicePrice";
            case 2:
                return "attr.deliveryServicePrice";
            case 3:
                return "attr.installationServicePrice";
            case 4:
                return "attr.fullServicePrice";
            default:
                return "attr.titleAttraction";
        }
    }

    @Override
    public List<Attraction> saveOrFind(Attraction[] attractions) {
        for (Attraction item : attractions) {
            try {
                repository.saveAndFlush(item);
            } catch (Exception ignored) {
                System.out.println(ignored);
            }
        }
        return findAll();
    }

    @Override
    public Attraction findByTitleAttraction(String title) {
        Attraction attraction = findByTitleAttraction(title);
        if (Objects.isNull(attraction)) {
            throw new EntityNotFoundException();
        }
        return attraction;
    }

    @Override
    @Transactional(noRollbackFor = ServerDataBaseException.class)
    public Attraction saveOrFind(Attraction attraction) {
        Attraction findAttr = null;
        try {
            if (!Objects.isNull(attraction.getTitleAttraction()))
                findAttr = repository.findByTitleAttraction(attraction.getTitleAttraction());

            if (!Objects.isNull(findAttr)) {
                attraction.setId(findAttr.getId());
            }

            attraction.setTypes(typeService.saveOrFind(attraction.getTypes()));
            attraction.setTags(tagService.saveOrFind(attraction.getTags()));

            repository.save(attraction);
        } catch (Exception ignored) {
            throw new ServerDataBaseException();
        }
        return attraction;
    }
}