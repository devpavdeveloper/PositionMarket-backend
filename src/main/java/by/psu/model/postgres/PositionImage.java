package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "position_image")
@Getter
@Setter
public class PositionImage {

    @ManyToOne
    private Attraction attraction;

    @ManyToOne
    private Image image;

    @Column(name = "main_image")
    private boolean mainImage;

}
