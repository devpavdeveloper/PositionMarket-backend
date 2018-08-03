package by.psu.service.facade;

import by.psu.service.dto.UserDTO;
import by.psu.service.dto.mappers.user.UserMapper;
import by.psu.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO update(UserDTO userDTO) {
        return userMapper.to(
                userService.update(
                        userMapper.from(userDTO)
                )
        );
    }

    @Transactional
    public UserDTO update(UserDTO userDTO, String username) {
        return userMapper.to(
                userService.update(
                        userMapper.from(userDTO), username
                )
        );
    }

    @Transactional(readOnly = true)
    public List<UserDTO> getAll() {
        return userService.getAll().stream().map(userMapper::to).collect(Collectors.toList());
    }
}
