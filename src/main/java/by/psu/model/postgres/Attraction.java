package by.psu.model.postgres;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(
        name = "attractions"
)
@Table(
        name = "attractions"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"title", "images", "tags", "types", "products"})
public class Attraction extends BasicEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title", nullable = false)
    private Translate title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description")
    private Translate description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "attraction")
    private List<PositionImage> images;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "attraction_tag", joinColumns = {
            @JoinColumn(name = "attraction", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag")})
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "attraction_type", joinColumns = {
            @JoinColumn(name = "attraction", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "type")})
    private List<Type> types;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "attraction_id")
    private List<Product> products;

}
