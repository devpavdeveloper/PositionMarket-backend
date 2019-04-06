package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(
        name = "images"
)
@Table(
        name = "images"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BasicEntity {

    @Column(name = "url", nullable = false)
    public String url;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "image")
    private PositionImage positionImage;

}
