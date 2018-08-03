package by.psu.model.postgres.repository;

import by.psu.model.postgres.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryAttraction extends JpaRepository<Attraction, UUID> { }