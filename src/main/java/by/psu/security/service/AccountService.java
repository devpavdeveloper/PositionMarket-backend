package by.psu.security.service;

import by.psu.security.PasswordResetRequest;
import by.psu.security.RegistrationRequest;
import by.psu.security.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface AccountService {
    public User register(RegistrationRequest registrationRequest, HttpHeaders httpHeaders);

    public User confirmRegistration(String token, String email);

    public User resetPassword(PasswordResetRequest passwordResetRequest);

    public ResponseEntity<?> forgotPassword(String email, HttpHeaders httpHeaders);
}