package by.psu.model.postgres;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(
        name = "products"
)
@Table(
        name = "products"
)
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"service", "price"})
public class Product extends BasicEntity {

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "id_service", nullable = false)
  private TypeService service;

  @Column(name = "price")
  private BigDecimal price;

}
