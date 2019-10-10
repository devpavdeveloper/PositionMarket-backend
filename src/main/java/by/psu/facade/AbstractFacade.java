package by.psu.facade;

import by.psu.mappers.AbstractMapper;
import by.psu.model.postgres.BasicEntity;
import by.psu.service.api.Service;
import by.psu.service.dto.AbstractDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Transactional
public abstract class AbstractFacade<T extends BasicEntity, DTO extends AbstractDTO> implements Facade<DTO, UUID> {

    private Service<T> service;
    private AbstractMapper<T, DTO> mapper;


    protected AbstractFacade(Service<T> service, AbstractMapper<T, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @Override
    public List<DTO> getAll() {
        return mapper.mapToDTOS(service.findAll());
    }

    public Page<DTO> findAll(Pageable pageable) {
        return service.findAll(pageable).map(mapper::map);
    }

    @Override
    public DTO getOne(UUID uuid) {
        T obj = service.findById(uuid);
        return mapper.map(obj);
    }

    @Override
    public DTO save(DTO object) {
        return action(object, service::save);
    }

    @Override
    public DTO update(DTO object) {
        return action(object, service::update);
    }

    private DTO action(final DTO obj, final Function<T, T> function) {
        T entity = mapper.map(obj);
        T actionEntity = function.apply(entity);
        return mapper.map(actionEntity);
    }

    @Override
    public void delete(UUID uuid) {
        service.delete(uuid);
    }

    @Override
    public void delete(List<UUID> uuids) {
        service.delete(uuids);
    }

}
