package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(
        name = "tags"
)
@Table(
        name = "tags"
)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tag extends Nsi {}
