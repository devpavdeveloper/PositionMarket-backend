package by.psu.model.postgres.repository;

import by.psu.constants.TypeService;
import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RepositoryProduct extends JpaRepository<Product, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM products WHERE attraction = ?1")
    List<Product> findAllProductByUUIDAttraction(UUID uuid);

}