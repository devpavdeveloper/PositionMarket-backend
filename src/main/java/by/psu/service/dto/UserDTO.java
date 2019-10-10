package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO extends AbstractDTO {
    private String username;
    private Boolean enabled;
    private UserProfileDTO information;
}
