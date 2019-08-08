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
        name = "positions"
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"title", "images", "tags", "types", "products"})
@ToString(exclude = {"title", "description", "images", "tags", "types", "products"}, callSuper = true)
public class Position extends BasicEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "title", nullable = false)
    private Translate title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "description")
    private Translate description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id")
    private List<PositionImage> images;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "position_tag", joinColumns = {
            @JoinColumn(name = "position_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "position_type", joinColumns = {
            @JoinColumn(name = "position_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private List<Type> types;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name="position_id")
    private List<Product> products;

}
