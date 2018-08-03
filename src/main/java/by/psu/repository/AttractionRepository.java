package by.psu.repository;

import by.psu.model.Attraction;
import by.psu.model.TypeAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    public List<Attraction> findAllByTypeAttractions(TypeAttraction typeAttractions);

    public List<Attraction> findAllByOrderByTitleAttractionAsc();

    public Attraction findByTitleAttraction(String title);
}
