package by.psu.service;

import by.psu.security.PasswordResetRequest;
import by.psu.security.RegistrationRequest;
import by.psu.security.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;

public interface AccountService {
    public User register(RegistrationRequest registrationRequest, HttpHeaders httpHeaders);

    public User confirmRegistration(String token, String email);

    public User resetPassword(PasswordResetRequest passwordResetRequest);

    public ResponseEntity<?> forgotPassword(String email, HttpHeaders httpHeaders);
}
