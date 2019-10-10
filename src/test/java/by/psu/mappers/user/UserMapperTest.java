package by.psu.mappers.user;

import by.psu.BaseTest;
import by.psu.model.postgres.UserProfile;
import by.psu.security.model.User;
import by.psu.service.dto.UserDTO;
import by.psu.service.dto.UserProfileDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    private final static String LOGIN = "devpav";
    private final static String EMAIL = "jsdeveloper@yahoo.com";
    private final static String PHONE_NUMBER = "+375299999999";


    @Test
    public void mapToDTO() {
        UserProfile userProfile = UserProfile.builder()
                .email(EMAIL)
                .phone(PHONE_NUMBER)
                .priceDistance(10)
                .build();


        User user = User.builder()
                .enabled(true)
                .login(LOGIN)
                .lastPasswordResetDate(new Date())
                .userProfile(userProfile)
                .build();

        UserDTO userDTO = userMapper.map(user);

        assertNotNull(userDTO);
        assertTrue(userDTO.getEnabled());
        assertNotNull(userDTO.getInformation());
        assertEquals(userDTO.getUsername(), LOGIN);

        assertEquals(userDTO.getInformation().getEmail(), EMAIL);
        assertEquals(userDTO.getInformation().getPhone(), PHONE_NUMBER);
        assertEquals(userDTO.getInformation().getPriceDistance(), new Integer(10));
    }

    @Test
    public void mapToEntity() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setEmail(EMAIL);
        userProfileDTO.setPhone(PHONE_NUMBER);
        userProfileDTO.setPriceDistance(10);

        UserDTO userDTO = new UserDTO();
        userDTO.setEnabled(true);
        userDTO.setInformation(userProfileDTO);
        userDTO.setUsername(LOGIN);

        User user = userMapper.map(userDTO);

        assertNotNull(user);

        assertTrue(user.getEnabled());
        assertEquals(user.getLogin(), LOGIN);
        assertNotNull(user.getUserProfile());
        assertEquals(user.getUserProfile().getEmail(), EMAIL);
        assertEquals(user.getUserProfile().getPhone(), PHONE_NUMBER);
        assertEquals(user.getUserProfile().getPriceDistance(), new Integer(10));
    }
}