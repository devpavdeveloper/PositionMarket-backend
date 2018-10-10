package by.psu.model.postgres.repository;

import by.psu.model.postgres.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryAttraction extends JpaRepository<Attraction, UUID> {}