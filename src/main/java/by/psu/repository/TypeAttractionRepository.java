package by.psu.repository;

import by.psu.model.TypeAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeAttractionRepository extends JpaRepository<TypeAttraction, Long> {
    public TypeAttraction findByEnTitle(String title);
    public TypeAttraction findByRuTitle(String title);
}
