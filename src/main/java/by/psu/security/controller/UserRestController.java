package by.psu.security.controller;

import by.psu.security.JwtTokenUtil;
import by.psu.security.JwtUser;
import by.psu.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/profile")
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserDetailsService userDetailsService;

    private final UserRepository userRepository;

    @Autowired
    public UserRestController(JwtTokenUtil jwtTokenUtil,
                              @Qualifier("jwtUserDetailsService") UserDetailsService userDetailsService, UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @GetMapping("/authority")
    public ResponseEntity isUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);

        return ResponseEntity.ok(userDetailsService.loadUserByUsername(username).getAuthorities());
    }

    @GetMapping("/info")
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }
}
