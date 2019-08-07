package by.psu.controllers.nsi;

import by.psu.controllers.AbstractResource;
import by.psu.facade.NsiFacade;
import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public class NsiController<T extends Nsi, E extends NsiDTO> extends AbstractResource<E> {

    public NsiController(NsiFacade<T, E> nsiFacade, Class<?> loggerClass) {
        super(nsiFacade, loggerClass);
    }

    @Override
    public ResponseEntity<List<E>> get() {
        return super.get();
    }

    @Override
    public ResponseEntity<E> get(UUID uuid) {
        return super.get(uuid);
    }

    @Override
    public ResponseEntity<E> create(@RequestBody E obj) {
        return super.create(obj);
    }

    @Override
    public ResponseEntity<E> update(@RequestBody E obj) {
        return super.update(obj);
    }

    @Override
    public ResponseEntity<E> delete(UUID uuid) {
        return super.delete(uuid);
    }

    @Override
    public ResponseEntity delete(UUID[] uuid) {
        return super.delete(uuid);
    }
}
