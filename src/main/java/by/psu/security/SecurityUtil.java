package by.psu.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class SecurityUtil {

    public Optional<String> getUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication)) {
            return Optional.empty();
        }

        final UserDetails user =  (UserDetails) authentication.getPrincipal();

        if (isNull(user)) {
            return Optional.empty();
        }

        return Optional.ofNullable(user.getUsername());
    }

}
