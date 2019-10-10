package by.psu.controllers;

import by.psu.facade.UserFacade;
import by.psu.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDTO update(@RequestBody UserDTO user, @PathVariable String username) {
        return userFacade.update(user, username);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    public UserDTO update(@RequestBody UserDTO user) {
        return userFacade.update(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAll() {
        return userFacade.getAll();
    }
}
