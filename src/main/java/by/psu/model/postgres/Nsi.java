package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter @Setter
@MappedSuperclass
public class Nsi extends BasicEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="title", nullable=false)
    private Translate title;

}
