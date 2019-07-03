package by.psu.mappers.user;

import by.psu.mappers.MapperConfiguration;
import by.psu.model.postgres.UserProfile;
import by.psu.service.dto.UserProfileDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface UserProfileMapper {

    public UserProfileDTO map(UserProfile userProfile);

    public UserProfile map(UserProfileDTO userProfileDTO);

}
