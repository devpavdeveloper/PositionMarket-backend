package by.psu.model.factory;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FactoryProduct {

    public Product create() {
        return new Product();
    }

    public Product create(Integer price, TypeService typeService) {
        Product product = create();
        product.setPrice( new BigDecimal(price) );
        product.setService( typeService );
        return product;
    }
}
