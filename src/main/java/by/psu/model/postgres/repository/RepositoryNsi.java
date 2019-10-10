package by.psu.model.postgres.repository;

import by.psu.model.postgres.Nsi;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryNsi<T extends Nsi> extends AbstractRepository<T> { }
