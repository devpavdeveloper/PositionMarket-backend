package by.psu.controllers.nsi;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.facade.NsiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class NsiController<T extends Nsi, E extends NsiDTO> {

    @Autowired
    protected NsiFacade<T, E> nsiFacade;

    @GetMapping
    public ResponseEntity<List<E>> get() {
        return ResponseEntity.ok(nsiFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<E> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(nsiFacade.getOne(id));
    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E obj) {
        return ResponseEntity.ok(nsiFacade.save(obj));
    }

    @PutMapping
    public ResponseEntity<E> update(@RequestBody E obj) {
        return ResponseEntity.ok(nsiFacade.update(obj));
    }

}
