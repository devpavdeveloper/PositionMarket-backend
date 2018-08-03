package by.psu.listeners;

import by.psu.email.EmailService;
import by.psu.email.Mail;
import by.psu.events.OnRegistrationComplete;
import by.psu.security.model.User;
import by.psu.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetHeaders;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationComplete>{

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationComplete event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationComplete event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        InternetHeaders internetHeaders = new InternetHeaders();
        internetHeaders.addHeader("Content-Type", "text/html; charset=UTF-8");

        String confirmationString = emailService.createConfirmationString(user, token, event.getAppUrl());

        Mail confirmationMessage = new Mail("no-reply@hurr.com", user.getEmail(), "Registration Confirmation", confirmationString);
        emailService.sendHtmlMessage(confirmationMessage);
    }
}
