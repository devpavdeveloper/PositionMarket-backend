package by.psu.service.impl;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.exceptions.SearchRequestException;
import by.psu.exceptions.ServerDataBaseException;
import by.psu.model.Attraction;
import by.psu.model.TypeAttraction;
import by.psu.repository.AttractionRepository;
import by.psu.repository.TypeAttractionRepository;
import by.psu.service.AttractionService;
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

    @Autowired
    public AttractionServiceImpl(AttractionRepository repository, EntityManager entityManager, TypeAttractionRepository typeAttractionRepository) {
        this.repository = repository;
        this.entityManager = entityManager;
        this.typeAttractionRepository = typeAttractionRepository;
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
    public ArrayList findAllFilter(String[] typeAttractions, Integer sortField, Integer direction) {
        List<TypeAttraction> list;
        StringBuilder buildTypeAttractionSql = new StringBuilder();
        if (Objects.isNull(typeAttractions)) {
            /*list = typeAttractionRepository.findAll();
            typeAttractions = new String[list.size()];
            for (int i = 0; i < list.size(); i++)
                typeAttractions[i] = list.get(i).getEnTitle();*/
        } else {
            buildTypeAttractionSql.append("JOIN attr.typeAttractions u WHERE ");
            buildTypeAttractionSql.append("(");
            for (int i = 0; i < typeAttractions.length; i++) {
                TypeAttraction typeAttraction = null;
                try {
                    typeAttraction = typeAttractionRepository.findByEnTitle(typeAttractions[i]);
                    buildTypeAttractionSql.append(String.format("u.id = %s", typeAttraction.getId().toString()));
                    if (i != typeAttractions.length - 1)
                        buildTypeAttractionSql.append(" or ");
                    else
                        buildTypeAttractionSql.append(")");
                } catch (RuntimeException ex) {
                    throw new SearchRequestException();
                }
            }
        }

        String req = "select distinct attr from Attraction attr " + buildTypeAttractionSql + " ORDER BY " + selectSort(sortField) + " " + ((direction == 0) ? "asc" : "desc");

        Query query = entityManager.createQuery(req);

        return new ArrayList<Attraction>(query.getResultList());
    }

    private String selectSort(Integer index) {
        switch (index) {
            case 1: return "attr.pickupServicePrice";
            case 2: return "attr.deliveryServicePrice";
            case 3: return "attr.installationServicePrice";
            case 4: return "attr.fullServicePrice";
            default: return "attr.titleAttraction";
        }
    }

    @Override
    public List<Attraction> createAll(Attraction[] attractions) {
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
}