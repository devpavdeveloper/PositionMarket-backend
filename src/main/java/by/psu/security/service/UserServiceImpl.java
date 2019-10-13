package by.psu.security.service;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.EntityNotFoundException;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Repository
@Service
public class UserServiceImpl implements UserService {

    private final TokenRepository tokenRepository;

    private final UserService userService;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoderBean;

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserServiceImpl(UserService userService,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoderBean,
                           TokenRepository tokenRepository,
                           JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoderBean = passwordEncoderBean;
        this.tokenRepository = tokenRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    public User findById(UUID id) {
        return userService.findById(id);
    }

    @Transactional
    @Override
    public User save(User user) {

        if (isNull(user)) {
            throw new BadRequestException("User mustn't be is null");
        }

        user.setPassword(passwordEncoderBean.encode(user.getPassword()));

        List<Role> listRoles = roleRepository.findAll();

        user.setAuthorities(listRoles);
        user.setLastPasswordResetDate(new Date());

        return userService.save(user);
    }

    @Override
    public User findByLogin(String login) {
        User user = userService.findByLogin(login);
        if (isNull(user))
            throw new EntityNotFoundException("User isn't found by login " + login);

        return user;
    }

    @Override
    public User update(User obj, UUID id) {
        return null;
    }

    @Override
    public void remove(UUID id) {
    }

    @Transactional(readOnly = true)
    @Override
    public User alreadyExists(User user) {
        if (isNull(user))
            throw new BadRequestException("Username mustn't be null");

        final User foundUser = userService.findByLogin(user.getLogin());

        if (isNull(foundUser))
            throw new EntityNotFoundException("User isn't found by login " + user.getLogin());

        return foundUser;
    }

    @Transactional
    @Override
    public void createVerificationToken(User user, String token) {
        final VerificationToken myToken = new VerificationToken(user,
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
        final VerificationToken foundVerificationToken = tokenRepository.findById(token.getId())
                .orElseThrow(() -> new EntityNotFoundException("Token isn't found by id " + token.getId()));

        tokenRepository.delete(foundVerificationToken);
    }

    @Override
    public User alreadyExistsByEmail(User user) {
        return new User();
    }

    @Override
    public Optional<User> getUser(HttpServletRequest request) {
        final String requestHeader = request.getHeader(tokenHeader);

        if (isNull(requestHeader)) {
            return Optional.empty();
        }

        final String token = requestHeader.substring(7);
        final String username = jwtTokenUtil.getUsernameFromToken(token);

        return Optional.ofNullable(userService.findByLogin(username));
    }

    @Override
    @Transactional
    public void setAuthoritiesUser(Role[] roles, String username) {
        final User user = userService.findByLogin(username);

        if ( isNull(user) )
            throw new BadRequestException("User [" + username + "] not found");

        final List<Role> roleList = Stream.of(roles)
                .filter(Objects::nonNull)
                .map(role -> roleRepository.findByTitle(role.getTitle()))
                .collect(Collectors.toList());

        user.setAuthorities(roleList);
        userService.save(user);
    }

    @Override
    public void setDiscountUser(Integer discount, String username) {
    }

    @Override
    public void setStatusUser(Boolean statusUser, String username) {
        User user = userService.findByLogin(username);

        if (isNull(user))
            throw new BadRequestException("User [" + username + "] not found");

        try {
            user.setEnabled(statusUser);
            userService.save(user);
        } catch (Exception e) {
            throw new BadRequestException("User [" + username + "] not be add new authorities");
        }
    }


    @Override
    public User updateUserData(HttpServletRequest request, User user) {
        Optional<User> optionalUser = getUser(request);

        if (!optionalUser.isPresent()) {
            throw new BadRequestException("User not found");
        }

        return userService.save(user);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return false;
    }
}
