package by.psu.model.postgres;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(
        name = "type_service"
)
@Table(
        name = "type_service"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TypeService extends Nsi {

    @OneToMany(mappedBy = "service", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

}
