package by.psu.security.repository;

import by.psu.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByTitle(String title);

}