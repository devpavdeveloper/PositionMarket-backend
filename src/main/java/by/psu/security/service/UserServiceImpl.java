package by.psu.security.service;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.exceptions.ServerDataBaseException;
import by.psu.exceptions.transaction.UserTransactionException;
import by.psu.security.JwtTokenUtil;
import by.psu.security.model.Role;
import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import by.psu.security.repository.RoleRepository;
import by.psu.security.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Repository
@Service
public class UserServiceImpl implements UserService {

    private final TokenRepository tokenRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoderBean;

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           PasswordEncoder passwordEncoderBean, TokenRepository tokenRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoderBean = passwordEncoderBean;
        this.tokenRepository = tokenRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByOrderByLoginAsc();
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Transactional
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoderBean.encode(user.getPassword()));
        List<Role> listRoles = roleRepository.findAll();
        user.setAuthorities(new ArrayList<>(listRoles));
        user.setLastPasswordResetDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findByLogin(String login) {
        User user =  userRepository.findByLogin(login);
        if (Objects.isNull(user))
            throw new EntityNotFoundException();

        return user;
    }

    @Override
    public User update(User obj, UUID id) {
        userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        obj.setId(id);
        return Optional.of(userRepository.save(obj)).orElseThrow(ServerDataBaseException::new);
    }

    @Override
    public void remove(UUID id) {}

    @Transactional
    @Override
    public User alreadyExists(User user) {
        return Optional.ofNullable(userRepository.findByLogin(user.getLogin())).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(user,
                passwordEncoderBean.encode(token),
                VerificationToken.VerificationTokenGenerationMode.STANDARD);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token, String email) {
        return null;
    }

    @Override
    public void deleteVerificationToken(VerificationToken token) {
        try {
            tokenRepository
                    .delete(tokenRepository.findById(token.getId())
                            .orElseThrow(EntityNotFoundException::new));
        } catch (RuntimeException ex) {
            throw new ServerDataBaseException();
        }
    }

    @Override
    public User alreadyExistsByEmail(User user) {
        return new User();
    }

    @Override
    public User getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return userRepository.findByLogin(username);
    }

    @Override
    @Transactional
    public void setAuthoritiesUser(Role[] roles, String username) {
        User user = userRepository.findByLogin(username);

        if (Objects.isNull(user))
            throw new UserTransactionException("User [" + username + "] not found");

        List<Role> roleList = new ArrayList<>();
        try {
            for (Role r : roles) {
                roleList.add(roleRepository.findByTitle(r.getTitle()));
            }
        } catch (Exception e) {
            throw new UserTransactionException("Role not found");
        }

        if (roleList.isEmpty())
            throw new UserTransactionException("List roles is empty");

        try {
            user.setAuthorities(roleList);
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserTransactionException("User [" + username + "] not be add new authorities");
        }
    }

    @Override
    public void setDiscountUser(Integer discount, String username) {
        if (discount < 0 || discount > 100)
            throw new UserTransactionException("discount < 0 or discount > 100");
        User user = userRepository.findByLogin(username);

        if (Objects.isNull(user))
            throw new UserTransactionException("User [" + username + "] not found");

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserTransactionException("User [" + username + "] not be add new authorities");
        }

    }

    @Override
    public void setStatusUser(Boolean statusUser, String username) {
        User user = userRepository.findByLogin(username);

        if (Objects.isNull(user))
            throw new UserTransactionException("User [" + username + "] not found");

        try {
            user.setEnabled(statusUser);
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserTransactionException("User [" + username + "] not be add new authorities");
        }
    }


    @Override
    public User updateUserData(HttpServletRequest request, User user) {
        User us = getUser(request);
        if (Objects.isNull(us)) {
            throw new UserTransactionException("User not found");
        }

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserTransactionException(e.getMessage());
        }

        return user;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }
/*
    @Override
    public Boolean existsByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserTransactionException("User with email");
        }
        return true;
    }*/
}
