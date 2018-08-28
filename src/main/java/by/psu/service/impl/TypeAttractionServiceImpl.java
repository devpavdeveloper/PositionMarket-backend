package by.psu.service.impl;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.exceptions.ServerDataBaseException;
import by.psu.model.Tag;
import by.psu.model.TypeAttraction;
import by.psu.repository.TagRepository;
import by.psu.repository.TypeAttractionRepository;
import by.psu.service.TagService;
import by.psu.service.TypeService;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
public class TypeAttractionServiceImpl implements TypeService {

    private final TypeAttractionRepository typeAttractionRepository;

    @Autowired
    public TypeAttractionServiceImpl(TypeAttractionRepository typeAttractionRepository) {
        this.typeAttractionRepository = typeAttractionRepository;
    }

    @Transactional
    public List<TypeAttraction> saveAll(TypeAttraction[] typeAttractions) {
        for (TypeAttraction t : typeAttractions) {
            try {
                typeAttractionRepository.saveAndFlush(t);
            } catch (Exception ignored) {
            }
        }
        return findAll();
    }

    @Override
    public List<TypeAttraction> findAll() {
        return typeAttractionRepository.findAll();
    }

    @Override
    public TypeAttraction findById(Long id) {
        return null;
    }

    @Override
    @Transactional(noRollbackFor = Exception.class)
    public TypeAttraction save(TypeAttraction obj) {
        TypeAttraction type = typeAttractionRepository.findByRuTitle(obj.getRuTitle());
        if (Objects.isNull(type)) {
            try {
                obj = typeAttractionRepository.save(obj);
            } catch (Exception ignore) {}
            return obj;
        }
        return type;
    }

    @Override
    public TypeAttraction update(TypeAttraction obj, Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Set<TypeAttraction> saveOrFind(Collection<TypeAttraction> typeAttractions) {
        Set<TypeAttraction> list = new HashSet<>();
        for (TypeAttraction type : typeAttractions) {
            list.add(save(type));
        }
        return list;
    }
}
