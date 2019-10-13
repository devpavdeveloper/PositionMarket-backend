package by.psu.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by stephan on 20.03.16.
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

    private UUID id;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Date lastPasswordResetDate;

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
