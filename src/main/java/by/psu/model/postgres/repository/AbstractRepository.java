package by.psu.model.postgres.repository;

import by.psu.model.postgres.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractRepository<T extends BasicEntity> extends JpaRepository<T, UUID> { }
