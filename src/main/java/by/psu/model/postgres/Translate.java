package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity(
        name = "translate"
)
@Table(
        name = "translate"
)
@Getter @Setter
public class Translate extends BasicEntity {

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "translate",
            orphanRemoval = true
    )
    List<StringValue> stringValues;

}
