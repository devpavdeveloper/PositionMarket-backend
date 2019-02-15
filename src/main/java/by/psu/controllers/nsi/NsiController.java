package by.psu.controllers.nsi;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.facade.NsiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NsiController<T extends Nsi, E extends NsiDTO> {

    @Autowired
    protected NsiFacade<T, E> nsiFacade;

    @GetMapping
    public ResponseEntity<List<E>> get() {
        return ResponseEntity.ok(nsiFacade.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<E> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(nsiFacade.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<E> create(@RequestBody E obj) {
        return ResponseEntity.ok(nsiFacade.save(obj));
    }

    @PutMapping
    public ResponseEntity<E> update(@RequestBody E obj) {
        return ResponseEntity.ok(nsiFacade.update(obj));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<E> delete(@PathVariable UUID uuid) {
        nsiFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/multiple/delete")
    public ResponseEntity<E> delete(@RequestBody List<String> uuids) {
        nsiFacade.deleteAll(uuids.stream().map(UUID::fromString).collect(Collectors.toList()));
        return ResponseEntity.ok().build();
    }

}
