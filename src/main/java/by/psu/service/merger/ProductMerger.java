package by.psu.service.merger;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMerger implements BaseMerger<Product> {

    @Autowired
    private AbstractNsiMerger<TypeService> typeServiceAbstractNsiMerger;

    @Override
    public Product merge(Product first, Product second) {

        first.setService( typeServiceAbstractNsiMerger.merge(first.getService(), second.getService()) );
        first.setPrice( second.getPrice() );

        return first;
    }
}
