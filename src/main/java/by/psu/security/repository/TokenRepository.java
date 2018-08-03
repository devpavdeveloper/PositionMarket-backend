package by.psu.security.repository;

import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken, Long>{

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}