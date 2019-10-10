package by.psu.model.postgres;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@EqualsAndHashCode(callSuper = true, exclude = "url")
@ToString(exclude = {"url", "name", "length"}, callSuper = true)
public class Image extends BasicEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "length", nullable = false)
    private Long length;

}
