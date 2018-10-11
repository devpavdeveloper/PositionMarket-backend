package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(
        name = "tags"
)
@Table(
        name = "tags"
)
@Getter @Setter
@AllArgsConstructor
public class Tag extends Nsi<Tag> {}
