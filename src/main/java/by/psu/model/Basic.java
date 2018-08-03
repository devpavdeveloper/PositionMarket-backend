package by.psu.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@MappedSuperclass
@EqualsAndHashCode(exclude = {"id"})
public class Basic {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}