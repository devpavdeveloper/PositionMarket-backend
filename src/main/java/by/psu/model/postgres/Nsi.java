package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class Nsi extends BasicEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "title", nullable = false)
    private Translate title;

}
