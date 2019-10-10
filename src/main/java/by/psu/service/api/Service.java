package by.psu.service.api;

import by.psu.model.postgres.BasicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface Service<T extends BasicEntity> {

    List<T> findAll();
    T findById(UUID id);
    Page<T> findAll(Pageable pageable);
    T save(T object);
    List<T> getReferences(List<UUID> uuids);
    T update(T object);
    void delete(UUID id);
    void delete(Iterable<UUID> ids);

}
