package by.psu.model.postgres;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(
        name = "tags"
)
@Table(
        name = "tags"
)
@AllArgsConstructor
public class Tag extends Nsi {}
