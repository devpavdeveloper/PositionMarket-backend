package by.psu.model.postgres.repository;

import by.psu.model.postgres.PositionImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryPositionImage extends JpaRepository<PositionImage, UUID> {
}
