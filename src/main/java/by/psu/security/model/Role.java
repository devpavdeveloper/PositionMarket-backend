package by.psu.security.model;

import by.psu.model.postgres.BasicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter @Setter
public class Role extends BasicEntity {

    @Column(name = "title", length = 50)
    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

}