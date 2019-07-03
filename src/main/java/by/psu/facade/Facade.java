package by.psu.facade;

import java.util.List;
import java.util.Optional;

public interface Facade<T, ID> {

    public List<T> getAll();

    public Optional<T> getOne(ID id);

    public Optional<T> save(T object);

    public Optional<T> update(T object);

    public void delete(ID id);

    public void delete(List<ID> ids);

}
