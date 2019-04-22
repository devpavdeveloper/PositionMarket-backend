package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(
        name = "type_service"
)
@Table(
        name = "type_service"
)
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TypeService extends Nsi {
    
}
