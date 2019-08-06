package by.psu.model.postgres.repository;

import by.psu.model.postgres.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryImage extends AbstractRepository<Image> {

    List<Image> findAllByLength(Long length);

}
