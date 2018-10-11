package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(
        name = "tags"
)
@Table(
        name = "tags"
)
@Getter @Setter
@AllArgsConstructor
public class Tag extends Nsi<Tag> {}
