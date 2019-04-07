package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@EqualsAndHashCode(callSuper = true)
public class Type extends Nsi {}
