package by.psu.controllers.nsi;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.facade.NsiFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class NsiController<T extends Nsi> {

    @Autowired
    protected NsiFacade<T> nsiFacade;

    @GetMapping
    public ResponseEntity<List<NsiDTO>> get() {
        return ResponseEntity.ok(nsiFacade.getAll());
    }
/*

    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(serviceNsi.getOne(id));
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T obj) {
        return ResponseEntity.ok(serviceNsi.save(obj));
    }

    @PutMapping
    public ResponseEntity<T> update(@RequestBody T obj) {
        return ResponseEntity.ok(serviceNsi.update(obj));
    }
*/

}
