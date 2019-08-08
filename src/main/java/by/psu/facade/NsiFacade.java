package by.psu.facade;

import by.psu.mappers.nsi.NsiMapper;
import by.psu.model.postgres.Nsi;
import by.psu.service.api.NsiService;
import by.psu.service.dto.NsiDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public abstract class NsiFacade<T extends Nsi, E extends NsiDTO> extends AbstractFacade<T, E> {

    public NsiFacade(NsiService<T> nsiService, NsiMapper<T, E> mapper) {
        super(nsiService, mapper);
    }

    @Override
    public List<E> getAll() {
        return super.getAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    public E getOne(UUID uuid) {
        return super.getOne(uuid);
    }

    @Override
    public E save(E object) {
        return super.save(object);
    }

    @Override
    public E update(E object) {
        return super.update(object);
    }

    @Override
    public void delete(UUID uuid) {
        super.delete(uuid);
    }

    @Override
    public void delete(List<UUID> uuids) {
        super.delete(uuids);
    }
}
