package by.psu.service.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private Boolean enabled;
    private UserProfileDTO information;
}
