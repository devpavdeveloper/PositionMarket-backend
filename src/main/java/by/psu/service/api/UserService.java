package by.psu.service.api;

import by.psu.exceptions.authorization.UserNotFoundException;
import by.psu.security.SecurityUtil;
import by.psu.security.model.User;
import by.psu.security.service.UserRepository;
import by.psu.service.merger.UserMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        User merge = userMerger.merge(getUser(username), user);
        return userRepository.save(merge);
    }

    @Transactional
    public User update(User user) {
        User merge = userMerger.merge(getUser(securityUtil.getUsername()), user);
        return userRepository.save(merge);
    }

    @Transactional(readOnly = true)
    public User getUser(String username) {
        return userRepository.findByLogin(
                Optional.of(username.toLowerCase()).orElseThrow(UserNotFoundException::new)
        );
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
