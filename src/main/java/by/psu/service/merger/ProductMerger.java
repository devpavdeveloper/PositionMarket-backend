package by.psu.service.merger;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ProductMerger implements BaseMerger<Product> {

    private final AbstractNsiMerger<TypeService> typeServiceAbstractNsiMerger;

    @Autowired
    public ProductMerger(AbstractNsiMerger<TypeService> typeServiceAbstractNsiMerger) {
        this.typeServiceAbstractNsiMerger = typeServiceAbstractNsiMerger;
    }

    public List<Product> merge(List<Product> first, List<Product> second) {
        List<Product> products = BaseMerger.super.merge(first, second);

        Map<UUID, Product> productMap = products.stream()
                .filter(Objects::nonNull)
                .filter(product -> product.getService() != null && product.getService().getId() != null)
                .collect(Collectors.toMap(product -> product.getService().getId(), product -> product, (x, x1) -> x1));

        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product merge(Product first, Product second) {

        first.setService( typeServiceAbstractNsiMerger.merge(first.getService(), second.getService()) );
        first.setPrice( second.getPrice() );

        return first;
    }
}
