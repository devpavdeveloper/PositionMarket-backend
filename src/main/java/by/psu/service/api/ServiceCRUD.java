package by.psu.service.api;

import by.psu.model.postgres.BasicEntity;

import java.util.List;
import java.util.UUID;

public interface ServiceCRUD<T extends BasicEntity> {

    public List<T> getAll();
    public T getOne(UUID id);
    public T save(T object);
    public T update(T object);
    public void delete(UUID id);

}
