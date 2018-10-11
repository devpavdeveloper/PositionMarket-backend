package by.psu.model.postgres;


import by.psu.constants.TypeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  @Enumerated(EnumType.STRING)
  @Column(name = "service")
  private TypeService service;

  @Column(name = "price")
  private BigDecimal price;

}
