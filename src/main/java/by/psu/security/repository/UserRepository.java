package by.psu.security.repository;

import by.psu.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByLogin(String login);

    User findByEmail(String email);

    List<User> findAllByOrderByLoginAsc();

    Boolean existsByEmail(String email);
}
