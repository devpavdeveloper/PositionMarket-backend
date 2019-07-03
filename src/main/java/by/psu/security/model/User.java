package by.psu.security.model;

import by.psu.model.postgres.BasicEntity;
import by.psu.model.postgres.UserProfile;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User extends BasicEntity {

    @Column(name = "login", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String login;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "id", nullable = false)
    private UserProfile userProfile;

    @Column(name = "password", length = 100)
    @NotNull
    @JsonIgnore
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "enabled")
    @NotNull
    private Boolean enabled;

    @Column(name="last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    public User(String login, String password){
        this.login = login;
        this.password = password;
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
}