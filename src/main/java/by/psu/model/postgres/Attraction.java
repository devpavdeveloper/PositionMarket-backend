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

  @Column(name = "title")
  private String title;

  @Column(name = "link_source")
  private String linkSource;

  @Column(name = "image")
  private String image;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "cv_position", joinColumns = {
          @JoinColumn(name = "attraction", nullable = false) },
          inverseJoinColumns = { @JoinColumn(name = "attraction") })
  private List<Tag> tags;

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "cv_position", joinColumns = {
          @JoinColumn(name = "attraction", nullable = false) },
          inverseJoinColumns = { @JoinColumn(name = "attraction") })
  private List<Type> types;

}
