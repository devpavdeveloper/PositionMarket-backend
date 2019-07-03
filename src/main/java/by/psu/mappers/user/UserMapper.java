package by.psu.mappers.user;

import by.psu.mappers.MapperConfiguration;
import by.psu.security.model.User;
import by.psu.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfiguration.class, uses = UserProfileMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "login", target = "username"),
            @Mapping(source = "userProfile", target = "information")
    })
    public UserDTO map(User user);

    @Mappings({
            @Mapping(source = "username", target = "login"),
            @Mapping(source = "information", target = "userProfile")
    })
    public User map(UserDTO userDTO);

}
