package by.psu.security.model;

import by.psu.model.Basic;
import by.psu.model.postgres.BasicEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "users")
public class User extends BasicEntity {

    @Column(name = "login", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String login;

    @Column(name = "password", length = 100)
    @NotNull
    @JsonIgnore
    @Size(min = 4, max = 100)
    private String password;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @Column(name="last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    public User(String login, String password, String email, String phone){
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.enabled = false;
    }
    public User(String login){
        this.login = login;
    }

    @JsonBackReference("user-authorities")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private List<Role> authorities;

    @OneToOne(mappedBy = "user")
    private VerificationToken verificationTokens;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                Objects.equals(getEnabled(), user.getEnabled()) &&
                Objects.equals(getLastPasswordResetDate(), user.getLastPasswordResetDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getEmail(), getPhone(), getEnabled(), getLastPasswordResetDate());
    }
}