package by.psu.security.repository;

import by.psu.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    User findByEmail(String email);

    List<User> findAllByOrderByLoginAsc();

    Boolean existsByEmail(String email);
}
