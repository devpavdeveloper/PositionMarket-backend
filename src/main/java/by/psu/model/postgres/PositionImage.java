package by.psu.model.postgres;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "position_image")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = {"image", "mainImage"})
public class PositionImage extends BasicEntity {

    @ManyToOne
    @JoinColumn(name = "image")
    private Image image;

    @Column(name = "main_image")
    private boolean mainImage;

}
