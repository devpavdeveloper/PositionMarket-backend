package by.psu.model.postgres;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(
        name = "tags"
)
@Table(
        name = "tags"
)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends Nsi {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "attraction_tag", joinColumns = {
            @JoinColumn(name = "tag", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "attraction")})
    private List<Attraction> attractions = new ArrayList<>();

}
