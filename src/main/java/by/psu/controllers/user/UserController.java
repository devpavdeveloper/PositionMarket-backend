package by.psu.controllers.user;

import by.psu.email.EmailService;
import by.psu.email.Mail;
import by.psu.model.Order;
import by.psu.repository.AuthorityRepository;
import by.psu.security.model.Role;
import by.psu.security.model.User;
import by.psu.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/profile")
public class UserController {

    private final UserService userService;

    private final AuthorityRepository authorityRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    public UserController(UserService userService, AuthorityRepository authorityRepository) {
        this.userService = userService;
        this.authorityRepository = authorityRepository;
    }

    @GetMapping
    public ResponseEntity<User> getUser(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getUser(request));
    }

    @PostMapping
    public ResponseEntity<User> getUser(HttpServletRequest request, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUserData(request, user));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{login}/authorities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAuthoritiesUser(@PathVariable("login") String login) {
        return ResponseEntity.ok(userService.findByLogin(login).getAuthorities());
    }

    @GetMapping("/authorities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAuthorities() {
        return ResponseEntity.ok(authorityRepository.findAll());
    }

    @PostMapping("/{username}/authorities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity setAuthoritiesUser(@PathVariable("username") String username, @RequestBody Role[] roles) {
        userService.setAuthoritiesUser(roles, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/discount")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity setDiscountUser(@RequestParam(value = "discount") Integer discount, @PathVariable("username") String username) {
        userService.setDiscountUser(discount, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{username}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity setEnableUser(@RequestParam(value = "enable") Boolean status, @PathVariable("username") String username) {
        userService.setStatusUser(status, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateUser(HttpServletRequest request, @RequestBody User user) {
        return ResponseEntity.ok( userService.updateUserData(request, user));
    }

    @PostMapping("/order")
    public ResponseEntity sendOrder(@RequestBody Order order) {
        Mail mail = new Mail();
        mail.setTo("jsdeveloper@yahoo.com");
        mail.setFrom("minskjavadeveloper@gmail.com");
        mail.setSubject("Заказ на Sumo.by");
        mail.setContent(emailService.createOrderEmail(order));
        emailService.sendHtmlMessage(mail);
        return ResponseEntity.ok().build();
    }
}
