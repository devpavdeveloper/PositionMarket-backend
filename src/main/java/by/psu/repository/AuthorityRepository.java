package by.psu.repository;

import by.psu.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Role, Long> {
}
