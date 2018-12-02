package by.psu.model.postgres.repository;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryAttraction extends JpaRepository<Attraction, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM attractions ORDER BY title ASC OFFSET :offs LIMIT :lim")
    List<Attraction> findAllOrderByTitle(@Param("offs") Integer offset, @Param("lim") Integer limit);
}