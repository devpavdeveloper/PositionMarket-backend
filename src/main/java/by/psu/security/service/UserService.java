package by.psu.security.service;

import by.psu.security.model.Role;
import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import by.psu.service.BasicService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends BasicService<User, UUID>{

    User save(User user);

    User findByLogin(String login);

    User alreadyExists(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token, String email);

    void deleteVerificationToken(VerificationToken token);

    User alreadyExistsByEmail(User user);

    Optional<User> getUser(HttpServletRequest request);

    void setAuthoritiesUser(Role[] roles, String username);

    void setDiscountUser(Integer discount, String username);

    void setStatusUser(Boolean statusUser, String username);

    User updateUserData(HttpServletRequest request, User user);

    Boolean existsByEmail(String email);

}
