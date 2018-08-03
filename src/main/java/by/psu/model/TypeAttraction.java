package by.psu.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type_attraction")
@Getter @Setter
public class TypeAttraction extends Basic {

    @Column(name = "ru_title")
    private String ruTitle;

    @Column(name = "en_title")
    private String enTitle;

    @JsonBackReference(value="type-attractions")
    @ManyToMany(mappedBy = "typeAttractions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Attraction> attractions = new HashSet<>();
}
