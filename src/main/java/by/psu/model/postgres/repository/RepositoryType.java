package by.psu.model.postgres.repository;

import by.psu.model.postgres.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryType extends JpaRepository<Type, UUID> {

    Type findByTitle(String title);

}