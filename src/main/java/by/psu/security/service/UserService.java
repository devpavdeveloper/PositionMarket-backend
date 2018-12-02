package by.psu.security.service;

import by.psu.security.model.Role;
import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import by.psu.service.BasicService;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface UserService extends BasicService<User, UUID>{

    public User save(User user);

    public User findByLogin(String login);

    public User alreadyExists(User user);

    public void createVerificationToken(User user, String token);

    public VerificationToken getVerificationToken(String token, String email);

    public void deleteVerificationToken(VerificationToken token);

    public User alreadyExistsByEmail(User user);

    User getUser(HttpServletRequest request);

    void setAuthoritiesUser(Role[] roles, String username);

    void setDiscountUser(Integer discount, String username);

    void setStatusUser(Boolean statusUser, String username);

    User updateUserData(HttpServletRequest request, User user);

    Boolean existsByEmail(String email);
}
