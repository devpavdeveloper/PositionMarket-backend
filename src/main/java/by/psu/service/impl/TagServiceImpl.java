package by.psu.service.impl;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.exceptions.ServerDataBaseException;
import by.psu.model.Tag;
import by.psu.repository.TagRepository;
import by.psu.repository.TypeAttractionRepository;
import by.psu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;
    private final TypeAttractionRepository typeAttractionRepository;

    @Autowired
    public TagServiceImpl(TagRepository repository, TypeAttractionRepository typeAttractionRepository) {
        this.repository = repository;
        this.typeAttractionRepository = typeAttractionRepository;
    }

    @Override
    public List<Tag> findAll() {
        return repository.findAll();
    }

    @Override
    public Tag findById(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public Tag save(Tag obj) {
        Tag tag = null;
        try {
            tag = repository.save(obj);
        } catch (Exception e) {
            throw new ServerDataBaseException();
        }
        return tag;
    }

    @Override
    public Tag update(Tag obj, Long id) {

        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public List<Tag> saveAll(Tag[] tags) {
        for (Tag tag : tags) {
            try {
                repository.saveAndFlush(tag);
            } catch (Exception ignored) {}
        }
        return findAll();
    }
}
