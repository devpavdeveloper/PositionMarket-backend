package by.psu.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetRequest {

    private String password;

    private String passwordConfirm;

    private String token;

    private String email;
}
