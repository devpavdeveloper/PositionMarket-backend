package by.psu.merger;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttractionMerger implements BaseMerger<Attraction> {

    private final TranslateObjectMerger translateObjectMerger;
    private final ProductMerger productMerger;

    @Autowired
    public AttractionMerger(TranslateObjectMerger translateObjectMerger, ProductMerger productMerger) {
        this.translateObjectMerger = translateObjectMerger;
        this.productMerger = productMerger;
    }

    @Override
    public Attraction merge(Attraction first, Attraction second) {
        Translate title =
                translateObjectMerger.merge(first.getTitle(), second.getTitle());

        first.setTitle(title);

        Translate description =
                translateObjectMerger.merge(first.getDescription(), second.getDescription());

        first.setDescription(description);

        first.getImages().clear();
        first.getImages().addAll(second.getImages());

        List<Product> products = productMerger.merge(first.getProducts(), second.getProducts());

        first.getProducts().clear();
        first.getProducts().addAll(products);

        return first;
    }
}
