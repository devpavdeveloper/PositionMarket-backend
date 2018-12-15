package by.psu.model.postgres;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Product extends BasicEntity {

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_service", nullable = false)
  private TypeService service;

  @Column(name = "price")
  private BigDecimal price;

}
