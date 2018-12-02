package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@MappedSuperclass
public abstract class Nsi extends BasicEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="title", nullable=false)
    private Translate title;

}
