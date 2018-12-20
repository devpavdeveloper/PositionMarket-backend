package by.psu.service.merger;

import by.psu.exceptions.authorization.UserNotFoundException;
import by.psu.security.SecurityUtil;
import by.psu.security.model.User;
import by.psu.security.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private UserRepository userRepository;

    public User update(User user, String username) {
        User findUser = getUser(username);
        //TODO('merge entities')
        return userRepository.save(findUser);
    }

    public User update(User user) {
        User findUser = getUser(securityUtil.getUsername());
        //TODO('merge entities')
        return userRepository.save(findUser);
    }

    public User getUser(String username) {
        return userRepository.findByLogin(
                Optional.of(username.toLowerCase()).orElseThrow(UserNotFoundException::new)
        );
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
