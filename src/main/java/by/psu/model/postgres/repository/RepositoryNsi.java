package by.psu.model.postgres.repository;

import by.psu.model.postgres.Nsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface RepositoryNsi<T extends Nsi> extends JpaRepository<T, UUID> { }
