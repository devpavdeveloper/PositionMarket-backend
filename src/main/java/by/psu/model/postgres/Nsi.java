package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public abstract class Nsi<T extends Nsi> extends BasicEntity {

    @Column(name = "title")
    private String title;

}
