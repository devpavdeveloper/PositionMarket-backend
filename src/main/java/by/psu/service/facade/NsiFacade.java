package by.psu.service.facade;

import by.psu.model.postgres.Nsi;
import by.psu.service.api.NsiService;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class NsiFacade <T extends Nsi, E extends NsiDTO> {

    protected final NsiService<T> nsiService;

    protected NsiMapper <T, E> mapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public NsiFacade(NsiService<T> nsiService, NsiMapper<T, E> mapper) {
        this.nsiService = nsiService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<E> getAll() {
        return nsiService.getAll().stream()
                .map(nsi -> mapper.map(nsi))
                .collect(Collectors.toList());
    }

    @Transactional
    public E getOne(UUID id) {
        return mapper.map(nsiService.getOne(id));
    }

    @Transactional
    public E save(E nsiDTO) {
        return mapper.map(nsiService.save(mapper.map(nsiDTO)));
    }

    @Transactional
    public E update(E nsiDTO) {
        return mapper.map(nsiService.update(mapper.map(nsiDTO)));
    }

    @Transactional
    public void delete(UUID uuid) {
        nsiService.delete(uuid);
    }

    @Transactional
    public void deleteAll(List<UUID> uuids) {
        nsiService.deleteAll(uuids);
    }
}
