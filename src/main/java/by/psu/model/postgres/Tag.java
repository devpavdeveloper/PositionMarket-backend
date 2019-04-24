package by.psu.model.postgres;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private Set<Attraction> attractions = new HashSet<>();

}
