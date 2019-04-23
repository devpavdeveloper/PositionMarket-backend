package by.psu.service.merger;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
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
        first.setTitle(translateObjectMerger.merge(first.getTitle(), second.getTitle()));

        first.getImages().clear();
        first.getImages().addAll( second.getImages() );

        List<Product> products = productMerger.merge(first.getProducts(), second.getProducts());

        first.getProducts().clear();
        first.getProducts().addAll( products );

        return first;
    }
}
