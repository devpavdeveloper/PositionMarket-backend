package by.psu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "tags")
@EqualsAndHashCode(callSuper = true)
public class Tag extends Basic {

    @Column(name = "ru_title")
    private String ruTitle;

    @Column(name = "en_title")
    private String enTitle;

    @JsonBackReference(value="tag-attractions")
    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Attraction> attractions = new HashSet<>();
}
