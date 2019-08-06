package by.psu.service.api;

import by.psu.model.postgres.PositionImage;
import by.psu.model.postgres.repository.RepositoryPositionImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionImageService extends AbstractService<PositionImage> {

    @Autowired
    public PositionImageService(RepositoryPositionImage repositoryPositionImage) {
        super(repositoryPositionImage, PositionImage.class);
    }

}
