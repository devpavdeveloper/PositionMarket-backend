package by.psu.repository;

import by.psu.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByRuTitle(String ruTitle);
}
