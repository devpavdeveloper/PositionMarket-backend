package by.psu.controllers.nsi;

import by.psu.controllers.AbstractResource;
import by.psu.facade.NsiFacade;
import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class NsiController<T extends Nsi, E extends NsiDTO> extends AbstractResource<E> {

    public NsiController(NsiFacade<T, E> nsiFacade, Class<?> loggerClass) {
        super(nsiFacade, loggerClass);
    }

    @Override
    public ResponseEntity<E> create(@RequestBody E obj) {
        return super.create(obj);
    }

    @Override
    public ResponseEntity<E> update(@RequestBody E obj) {
        return super.update(obj);
    }

}
