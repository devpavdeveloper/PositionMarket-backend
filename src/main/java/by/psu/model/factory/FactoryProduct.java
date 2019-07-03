package by.psu.model.factory;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class FactoryProduct {

    public Product create() {
        return new Product();
    }

    public Product create(UUID uuid, Integer price, TypeService typeService) {
        Product product = create(price, typeService);
        product.setId(uuid);
        return product;
    }

    public Product create(Integer price) {
        Product product = create();
        product.setPrice( new BigDecimal(price) );
        return product;
    }

    public Product create(Integer price, Long order) {
        Product product = create(price);
        product.setOrder(order);
        return product;
    }

    public Product create(Integer price, TypeService typeService) {
        Product product = create(price);
        product.setService( typeService );
        return product;
    }

}
