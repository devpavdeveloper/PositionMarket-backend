package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity(
        name = "attractions"
)
@Table(
        name = "attractions"
)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attraction extends BasicEntity {


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="title", nullable=false)
  private Translate title;

  @Column(name = "link_source")
  private String linkSource;

  @Column(name = "image")
  private String image;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "attraction_tag", joinColumns = {
          @JoinColumn(name = "attraction", nullable = false) },
          inverseJoinColumns = { @JoinColumn(name = "tag") })
  private List<Tag> tags;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "attraction_type", joinColumns = {
          @JoinColumn(name = "attraction", nullable = false) },
          inverseJoinColumns = { @JoinColumn(name = "type") })
  private List<Type> types;

  @LazyCollection(LazyCollectionOption.FALSE)
  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  @JoinColumn(name = "attraction")
  private List<Product> products;
}
