package by.psu.controllers;

import by.psu.service.dto.UserDTO;
import by.psu.service.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user, @PathVariable String username) {
        return ResponseEntity.ok(userFacade.update(user, username));
    }

    @PostMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userFacade.update(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userFacade.getAll());
    }
}
