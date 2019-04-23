package by.psu.service.api;

import by.psu.model.postgres.PositionImage;
import by.psu.model.postgres.repository.RepositoryPositionImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PositionImageService implements ServiceCRUD<PositionImage> {

    private final RepositoryPositionImage repositoryPositionImage;

    @Autowired
    public PositionImageService(RepositoryPositionImage repositoryPositionImage) {
        this.repositoryPositionImage = repositoryPositionImage;
    }


    @Override
    @Transactional
    public List<PositionImage> getAll() {
        return null;
    }

    @Override
    @Transactional
    public PositionImage getOne(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public PositionImage save(PositionImage object) {
        return repositoryPositionImage.save(object);
    }

    @Override
    public PositionImage update(PositionImage object) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
