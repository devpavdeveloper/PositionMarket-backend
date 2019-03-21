package by.psu.model.postgres.repository;

import by.psu.model.postgres.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryImage extends JpaRepository<Image, UUID> {
}
