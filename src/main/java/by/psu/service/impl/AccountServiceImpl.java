package by.psu.service.impl;

import by.psu.events.OnPasswordRecoveryInitiate;
import by.psu.events.OnRegistrationComplete;
import by.psu.exceptions.*;
import by.psu.security.PasswordResetRequest;
import by.psu.security.RegistrationRequest;
import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import by.psu.security.service.UserService;
import by.psu.service.AccountService;
import by.psu.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(ApplicationEventPublisher eventPublisher, UserService userService, PasswordEncoder passwordEncoder) {
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegistrationRequest registrationRequest, HttpHeaders httpHeaders) {

        User user = new User(
                registrationRequest.getUsername(),
                registrationRequest.getPassword(),
                registrationRequest.getEmail(),
                registrationRequest.getPhone()
        );

        try {
            user = userService.alreadyExists(user);
        } catch(EntityNotFoundException ex){
            try{
                userService.alreadyExistsByEmail(user);
            } catch(EntityNotFoundException e){
                user = userService.save(user);
                eventPublisher.publishEvent(new OnRegistrationComplete(user, httpHeaders.getHost().getHostName()));
                return user;
            }
            throw new DuplicateEmailException("Данный email уже зарегистрирован другим пользователем");
        }
        throw new DuplicateUserException("Пользователь с указанным именем уже существует");
    }

    @Override
    public User confirmRegistration(String token, String email) {

        VerificationToken vt = userService.getVerificationToken(token, email);

        User user = vt.getUser();

        if(Util.isOverTime(vt.getExpirationDate().getTime())) {
            throw new TokenExpiredException();
        }
        else{
            user.setEnabled(true);
            userService.update(user, user.getId());
            userService.deleteVerificationToken(vt);
            return user;
        }
    }

    @Override
    public User resetPassword(PasswordResetRequest passwordResetRequest) {

        VerificationToken vt = userService.getVerificationToken(passwordResetRequest.getToken(), passwordResetRequest.getEmail());

        User user = vt.getUser();

        if(Util.isOverTime(vt.getExpirationDate().getTime())){
            throw new TokenExpiredException();
        }
        else{
            user.setEnabled(true);
            user.setPassword(passwordEncoder.encode(passwordResetRequest.getPassword()));
            user.setLastPasswordResetDate(new Date());
            userService.update(user, user.getId());
            userService.deleteVerificationToken(vt);
            return user;
        }
    }
    @Override
    public ResponseEntity<?> forgotPassword(String email, HttpHeaders httpHeaders) {
        User user = new User();
        user.setEmail(email);
        if((user = userService.alreadyExistsByEmail(user)) != null){
            //password reset allowed only once every 24 hours
            //allow

                if(user.getEnabled()){
                    user.setEnabled(false);
                    eventPublisher.publishEvent(new OnPasswordRecoveryInitiate(user, httpHeaders.getHost().getHostName()));
                    //send email
                }
                else{
                    throw new UserNotActiveException("Пользователь не активен");
                    //reject, user not activated -> unable to recover
                }

        }
        else{
            throw new EntityNotFoundException("Пользователь не существует");
            //reject, user does not exist
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
