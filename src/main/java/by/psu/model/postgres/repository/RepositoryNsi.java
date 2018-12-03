package by.psu.model.postgres.repository;

import by.psu.model.postgres.Nsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

@NoRepositoryBean
public interface RepositoryNsi<T extends Nsi> extends JpaRepository<T, UUID> {
    /*@Query(value = "select cl from :class cl JOIN cl.getTitle() t JOIN t.getStringValues() v WHERE lower(v.value) LIKE %:value%")
    T findByTitle(@Param("value")String value, @Param("class") Class<T> obj);*/
}
