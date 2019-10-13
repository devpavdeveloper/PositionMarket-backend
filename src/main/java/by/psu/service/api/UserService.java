package by.psu.service.api;

import by.psu.exceptions.BadRequestException;
import by.psu.exceptions.authorization.UserNotFoundException;
import by.psu.merger.UserMerger;
import by.psu.security.SecurityUtil;
import by.psu.security.model.User;
import by.psu.security.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class UserService {

    private final SecurityUtil securityUtil;
    private final UserRepository userRepository;
    private final UserMerger userMerger;

    @Autowired
    public UserService(SecurityUtil securityUtil, UserRepository userRepository, UserMerger userMerger) {
        this.securityUtil = securityUtil;
        this.userRepository = userRepository;
        this.userMerger = userMerger;
    }


    @Transactional
    public User update(User user, String username) {
        if (isNull(user)) {
            throw new BadRequestException("User mustn't be null");
        }

        final User foundUser = getUser(username);

        if (isNull(foundUser)) {
            throw new UserNotFoundException();
        }

        final User merge = userMerger.merge(foundUser, user);

        return userRepository.save(merge);
    }

    @Transactional
    public User update(User user) {
        final Optional<String> optionalUsername = securityUtil.getUsername();

        if (!optionalUsername.isPresent()) {
            throw new BadRequestException("Username from authorization mustn't be null");
        }

        return this.update(user, optionalUsername.get());
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        if (isNull(username))
            throw new BadRequestException("Username mustn't be null");

        return userRepository.findByLogin(username);
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
