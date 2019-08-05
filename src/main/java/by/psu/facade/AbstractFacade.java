package by.psu.facade;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.mappers.AbstractMapper;
import by.psu.model.postgres.BasicEntity;
import by.psu.service.api.ServiceCRUD;
import by.psu.service.dto.AbstractDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public abstract class AbstractFacade<T extends BasicEntity, DTO extends AbstractDTO> implements Facade<DTO, UUID> {

    private ServiceCRUD<T> service;
    private AbstractMapper<T, DTO> mapper;

    public AbstractFacade(ServiceCRUD<T> service, AbstractMapper<T, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<DTO> getAll() {
        return service.getAll().stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public DTO getOne(UUID uuid) {
        T obj = service.getOne(uuid).orElseThrow(() ->
                new EntityNotFoundException(String.format("Entity uuid [%s]", uuid.toString())));
        return mapper.map(obj);
    }

    @Override
    public DTO save(DTO object) {
        return service.save(mapper.map(object)).map(mapper::map).orElseThrow(() ->
                new RuntimeException("Entity can't be save"));
    }

    @Override
    public DTO update(DTO object) {

        if ( isNull(object) || isNull(object.getId()) ) {
            return Optional.empty();
        }

        return repository.findById(object.getId())
                .map(t -> repository.save(updatePlace(t, object)));
    }

    protected abstract DTO updatePlace(T oldEntity, T newEntity);

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void delete(List<UUID> uuids) {

    }
}
