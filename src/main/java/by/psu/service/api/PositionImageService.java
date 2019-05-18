package by.psu.service.api;

import by.psu.model.postgres.PositionImage;
import by.psu.model.postgres.repository.RepositoryPositionImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
public class PositionImageService implements ServiceCRUD<PositionImage> {

    private final RepositoryPositionImage repositoryPositionImage;

    @Autowired
    public PositionImageService(RepositoryPositionImage repositoryPositionImage) {
        this.repositoryPositionImage = repositoryPositionImage;
    }


    @Override
    @Transactional(readOnly = true)
    public List<PositionImage> getAll() {
        return repositoryPositionImage.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PositionImage> getOne(UUID id) {
        return repositoryPositionImage.findById(id);
    }

    @Override
    @Transactional
    public Optional<PositionImage> save(PositionImage object) {
        repositoryPositionImage.save(object);
        return null;
    }

    @Override
    @Transactional
    public Optional<PositionImage> update(PositionImage object) {
        return null;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        repositoryPositionImage.deleteById(id);
    }

    @Override
    @Transactional
    public void delete(Iterable<UUID> ids) {

        if ( isNull(ids) ) {
            return;
        }

        ids.iterator()
                .forEachRemaining(this::delete);
    }
}
