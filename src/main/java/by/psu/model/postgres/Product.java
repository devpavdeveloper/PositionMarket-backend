package by.psu.model.postgres;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@ToString(exclude = {"service", "price", "order"})
public class Product extends BasicEntity {

  @LazyCollection(LazyCollectionOption.FALSE)
  @ManyToOne
  @JoinColumn(name = "id_service")
  @JsonBackReference("product-service")
  private TypeService service;

  @Column(name = "price")
  private BigDecimal price;

  @Column(name = "order_value")
  private Long order;


  @JsonIgnore
  @ManyToOne()
  @JoinColumn(name="position_id")
  private Attraction attraction;

}
