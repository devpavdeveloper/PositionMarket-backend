package by.psu.controllers;

import by.psu.facade.AbstractFacade;
import by.psu.model.postgres.BasicEntity;
import by.psu.service.dto.AbstractDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public abstract class AbstractResource<D extends AbstractDTO> {

    protected final Logger logger;

    private final AbstractFacade<? extends BasicEntity, D> abstractFacade;
    private final Class<?> loggerClass;

    public AbstractResource(AbstractFacade<? extends BasicEntity, D> abstractFacade, Class<?> loggerClass) {
        this.abstractFacade = abstractFacade;
        this.loggerClass = loggerClass;
        this.logger = LoggerFactory.getLogger(loggerClass);
        this.logger.info("Constructor AbstractResource initialized");
    }

    @GetMapping
    public ResponseEntity<List<D>> get() {
        logger.info("Get all entities [{}]", loggerClass);

        return ResponseEntity.ok(abstractFacade.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<D> get(@PathVariable("uuid") UUID uuid) {
        logger.info("Get entity [{}] by id {}", loggerClass, uuid);

        return ResponseEntity.ok(abstractFacade.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<D> create(D obj) {
        logger.info("Create entity [{}] body {}", loggerClass, obj);

        return ResponseEntity.ok(abstractFacade.save(obj));
    }

    @PutMapping
    public ResponseEntity<D> update(D obj) {
        logger.info("Update entity [{}] body {}", loggerClass, obj);

        return ResponseEntity.ok(abstractFacade.update(obj));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<D> delete(@PathVariable UUID uuid) {
        logger.info("Delete entity [{}] by id {}", loggerClass, uuid);

        abstractFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/[{uuid}]")
    public ResponseEntity delete(@PathVariable UUID[] uuid) {
        logger.info("Delete entities [{}] by ids {}", loggerClass, uuid);

        abstractFacade.delete(Arrays.asList(uuid));
        return ResponseEntity.ok().build();
    }

}
