package by.psu.model.postgres.repository;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryAttraction extends JpaRepository<Attraction, UUID> {



}