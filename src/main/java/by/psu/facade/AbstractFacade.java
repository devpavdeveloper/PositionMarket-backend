package by.psu.facade;

import by.psu.model.postgres.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

public abstract class AbstractFacade<T extends BasicEntity> implements Facade<T, UUID> {

    private JpaRepository<T, UUID> repository;

    public AbstractFacade(JpaRepository<T, UUID> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> getOne(UUID uuid) {
        return repository.findById(uuid);
    }

    @Override
    public Optional<T> save(T object) {

        if ( isNull(object) ) {
            return Optional.empty();
        }

        return Optional.of(repository.save(object));
    }

    @Override
    public Optional<T> update(T object) {

        if ( isNull(object) || isNull(object.getId()) ) {
            return Optional.empty();
        }

        return repository.findById(object.getId())
                .map(t -> repository.save(updatePlace(t, object)));
    }

    protected abstract T updatePlace(T oldEntity, T newEntity);

    @Override
    public void delete(UUID uuid) {
        repository.deleteById(uuid);
    }

    @Override
    public void delete(List<UUID> uuids) {
        //repository.deleteAll(uuids);
    }
}
