package by.psu.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final UUID id;
    private final String login;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private final Date lastPasswordResetDate;

    public JwtUser(
          UUID id,
          String login,
          String password,
          Collection<? extends GrantedAuthority> authorities,
          boolean enabled,
          Date lastPasswordResetDate
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }


    @JsonIgnore
    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
}
