package by.psu.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public String getUsername() {
        UserDetails user =  (UserDetails)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }
}
