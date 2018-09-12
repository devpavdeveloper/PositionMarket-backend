package by.psu.security.controller;

import by.psu.exceptions.TokenExpiredException;
import by.psu.security.*;
import by.psu.security.model.User;
import by.psu.security.model.VerificationToken;
import by.psu.security.service.JwtAuthenticationResponse;
import by.psu.security.service.UserService;
import by.psu.service.AccountService;
import by.psu.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@CrossOrigin
@RestController
public class AuthenticationRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${spring.registration_confirmation_redirect}")
    private String springRegistrationConfirmationRedirect;

    @Value("${spring.registration_confirmation_redirect}")
    private String springPasswordResetConfirmationRedirect;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    AccountService accountService;

    @Autowired
    ApplicationEventPublisher eventPublisher;


    @Autowired
    UserService userService;

    private final UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager,
                                        JwtTokenUtil jwtTokenUtil,
                                        @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        by.psu.security.model.User user = new by.psu.security.model.User(authenticationRequest.getUsername());
        User findUser = null;
        try {
            findUser = userService.alreadyExists(user);
        } catch (Exception e) {
            throw new AuthenticationException("Проверьте введенные данные", e);
        }

        if (findUser.getEnabled()) {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            // Reload password post-security so we can generate the token
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            //check if you have granted authorities here
            final String token = jwtTokenUtil.generateToken(userDetails);

            // Return the token
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/auth/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest registrationRequest, @RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.ok(accountService.register(registrationRequest, httpHeaders));

    }

    @GetMapping("/auth/email/exists")
    public ResponseEntity<?> existsEmail(@RequestParam("email") String email) {
        boolean existUser = userService.existsByEmail(email);
        return ResponseEntity.ok(existUser);
    }

    @GetMapping("/auth/register")
    public ResponseEntity<?> registrationConfirmation(@RequestParam("token") String token, @RequestParam("email") String email, HttpServletResponse response) {
        accountService.confirmRegistration(token, email);
        try {
            response.sendRedirect(springRegistrationConfirmationRedirect);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();

    }

    @PostMapping("/auth/reset_password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest, HttpServletResponse response) {
        accountService.resetPassword(passwordResetRequest);
        return ResponseEntity.ok().build();
    }

    //actual account recovery page
    @GetMapping("/auth/account_recovery")
    public ResponseEntity<?> recoverAccount(@RequestParam("token") String token, @RequestParam("email") String email, HttpServletResponse response) {
        VerificationToken vt = userService.getVerificationToken(token, email);
        by.psu.security.model.User user = vt.getUser();
        if (Util.isOverTime(vt.getExpirationDate().getTime())) {
            throw new TokenExpiredException();
        }
        try {
            springRegistrationConfirmationRedirect =
                    springRegistrationConfirmationRedirect.substring(1, springPasswordResetConfirmationRedirect.length() - 1);
            String url = springRegistrationConfirmationRedirect + "?token=" + token + "&email=" + user.getEmail();
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //user entered email and clicked send activation with password recovery instructions
    @PostMapping("/auth/forgot_password")
    public ResponseEntity<?> forgotPassword(@RequestParam("email") String email,
                                            @RequestHeader HttpHeaders httpHeaders) {
        return ResponseEntity.ok(accountService.forgotPassword(email, httpHeaders));
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("Пользователь временно заблокирован", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Неправильный логин или пароль", e);
        }
    }
}
