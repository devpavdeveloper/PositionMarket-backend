package by.psu.service.api;

import by.psu.model.postgres.BasicEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServiceCRUD<T extends BasicEntity> {

    List<T> getAll();
    Optional<T> getOne(UUID id);
    Optional<T> save(T object);
    Optional<T> update(T object);
    void delete(UUID id);
    void delete(Iterable<UUID> ids);

}
