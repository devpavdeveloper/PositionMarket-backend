package by.psu.service.api;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.EntityNotFoundException;
import by.psu.model.postgres.BasicEntity;
import by.psu.model.postgres.repository.AbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class AbstractService<T extends BasicEntity> implements Service<T> {

    private final Logger logger;


    private final AbstractRepository<T> abstractRepository;
    private final Class<?> loggerClass;


    public AbstractService(AbstractRepository<T> abstractRepository, Class<T> loggerClass) {
        this.abstractRepository = abstractRepository;
        logger =  LoggerFactory.getLogger(loggerClass.getName());
        this.loggerClass = loggerClass;
    }


    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        logger.info("Get all entities {}", loggerClass);
        return abstractRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public T findById(UUID id) {
        logger.info("Get entity [{}] by id {} entities", loggerClass, id);
        return abstractRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity isn't found by id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        logger.info("findAll entities [{}] with pageable", loggerClass, pageable);
        return abstractRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public T save(T object) {
        logger.info("Save entity [{}]", object);

        if (isNull(object) || nonNull(object.getId())) {
            throw new BadRequestException("Entity can't saves, because entity is NULL or has ID is't NULL");
        }

        return abstractRepository.save(object);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getReferences(List<UUID> uuids) {
        if (isNull(uuids) || uuids.isEmpty()) {
            return Collections.emptyList();
        }

        return uuids.stream()
                .map(abstractRepository::getOne)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public T update(T object) {
        logger.info("Update entity [{}]", object);

        if (isNull(object) || isNull(object.getId())) {
            throw new BadRequestException("Entity can't saves, because entity is NULL or has ID is NULL");
        }
        return abstractRepository.save(object);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        logger.info("Delete entity [{}] by id [{}]", loggerClass, id);

        if (isNull(id)) {
            throw new BadRequestException("ID mustn't be NULL");
        }

        abstractRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Iterable<UUID> ids) {
        logger.info("Delete entities [{}] by ids [{}]", loggerClass, ids);

        if (isNull(ids) || !ids.iterator().hasNext()) {
            throw new BadRequestException("Set ids mustn't be NULL or empty");
        }

        List<T> objects = new ArrayList<>();

        for (UUID id : ids) {
            T object = abstractRepository.getOne(id);
            if (nonNull(object)) {
                objects.add(object);
            }
        }

        abstractRepository.deleteAll(objects);
    }

}
