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

public abstract class AbstractResource<DTO extends AbstractDTO> {

    protected final Logger logger;

    private final AbstractFacade<? extends BasicEntity, DTO> abstractFacade;
    private final Class<?> loggerClass;

    public AbstractResource(AbstractFacade<? extends BasicEntity, DTO> abstractFacade, Class<?> loggerClass) {
        this.abstractFacade = abstractFacade;
        this.loggerClass = loggerClass;
        this.logger = LoggerFactory.getLogger(loggerClass);
    }

    @GetMapping
    public ResponseEntity<List<DTO>> get() {
        return ResponseEntity.ok(abstractFacade.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DTO> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(abstractFacade.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO obj) {
        return ResponseEntity.ok(abstractFacade.save(obj));
    }

    @PutMapping
    public ResponseEntity<DTO> update(@RequestBody DTO obj) {
        return ResponseEntity.ok(abstractFacade.update(obj));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<DTO> delete(@PathVariable UUID uuid) {
        abstractFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/[{uuid}]")
    public ResponseEntity delete(@PathVariable UUID[] uuid) {
        abstractFacade.delete(Arrays.asList(uuid));
        return ResponseEntity.ok().build();
    }
}
