package by.psu.merger;

import by.psu.model.postgres.Position;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PositionMerger implements BaseMerger<Position> {

    private final TranslateObjectMerger translateObjectMerger;
    private final ProductMerger productMerger;

    @Autowired
    public PositionMerger(TranslateObjectMerger translateObjectMerger, ProductMerger productMerger) {
        this.translateObjectMerger = translateObjectMerger;
        this.productMerger = productMerger;
    }

    @Override
    public Position merge(Position first, Position second) {
        Translate title =
                translateObjectMerger.merge(first.getTitle(), second.getTitle());

        first.setTitle(title);

        Translate description =
                translateObjectMerger.merge(first.getDescription(), second.getDescription());

        first.setDescription(description);

        first.getImages().clear();
        first.getImages().addAll(second.getImages());

        List<Product> originProduct = first.getProducts();

        List<Product> products = productMerger.merge(originProduct, second.getProducts());

        first.getProducts().clear();
        first.getProducts().addAll(products);

        return first;
    }
}
