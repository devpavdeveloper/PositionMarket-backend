package by.psu.controllers.nsi;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.facade.NsiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class NsiController<T extends Nsi, E extends NsiDTO> {

    protected NsiFacade<T, E> nsiFacade;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public NsiController(NsiFacade<T, E> nsiFacade) {
        this.nsiFacade = nsiFacade;
    }

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

    @DeleteMapping("/[{uuid}]")
    public ResponseEntity delete(@PathVariable UUID[] uuid) {
        nsiFacade.deleteAll(Arrays.asList(uuid));
        return ResponseEntity.ok().build();
    }

}
