package by.psu.service.dto.mappers.user;

import by.psu.security.model.User;
import by.psu.service.dto.UserDTO;
import by.psu.service.dto.mappers.MapperConfiguration;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapperConfiguration.class, uses = UserProfileMapper.class)
public interface UserMapper {

    @Mappings({
            @Mapping(source = "login", target = "username"),
            @Mapping(source = "userProfile", target = "information")
    })
    public UserDTO to(User user);

    @InheritConfiguration
    public User from(UserDTO userDTO);
}
