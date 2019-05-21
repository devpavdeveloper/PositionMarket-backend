package by.psu.model.postgres;

import lombok.*;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "service", orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Product> products = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 10)
    private TypeServiceEnum type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description")
    private Translate description;

}
