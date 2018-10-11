package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(
        name = "types"
)
@Table(
        name = "types"
)
@Getter @Setter
@AllArgsConstructor
public class Type extends Nsi<Type> { }
