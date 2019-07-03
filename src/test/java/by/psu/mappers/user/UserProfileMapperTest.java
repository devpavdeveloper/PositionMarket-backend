package by.psu.mappers.user;

import by.psu.BaseTest;
import by.psu.model.postgres.UserProfile;
import by.psu.service.dto.UserProfileDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class UserProfileMapperTest extends BaseTest {

    @Autowired
    private UserProfileMapper userProfileMapper;

    private final static String EMAIL = "jsdeveloper@yahoo.com";
    private final static String PHONE_NUMBER = "+375299999999";

    @Test
    public void mapToDTO() {
        UserProfile userProfile = UserProfile.builder()
                .priceDistance(10)
                .phone(PHONE_NUMBER)
                .email(EMAIL)
                .build();

        UserProfileDTO userProfileDTO = userProfileMapper.map(userProfile);

        assertEquals(userProfileDTO.getEmail(), EMAIL);
        assertEquals(userProfileDTO.getPriceDistance(), new Integer(10));
        assertEquals(userProfileDTO.getPhone(), PHONE_NUMBER);
    }

    @Test
    public void mapToEntity() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setPhone(PHONE_NUMBER);
        userProfileDTO.setEmail(EMAIL);
        userProfileDTO.setPriceDistance(10);

        UserProfile userProfile = userProfileMapper.map(userProfileDTO);

        assertEquals(userProfile.getEmail(), EMAIL);
        assertEquals(userProfile.getPriceDistance(), new Integer(10));
        assertEquals(userProfile.getPhone(), PHONE_NUMBER);
    }
}