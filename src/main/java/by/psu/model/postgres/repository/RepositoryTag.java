package by.psu.model.postgres.repository;

import by.psu.model.postgres.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryTag extends JpaRepository<Tag, UUID> {

     Tag findByTitle(String title);

}