package by.psu.service.merger;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.Tag;
import by.psu.model.postgres.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttractionMerger implements BaseMerger<Attraction> {

    @Autowired
    private TranslateObjectMerger translateObjectMerger;
    @Autowired
    private AbstractNsiMerger<Tag> tagAbstractNsiMerger;
    @Autowired
    private AbstractNsiMerger<Type> typeAbstractNsiMerger;
    @Autowired
    private ProductMerger productMerger;

    @Override
    public Attraction merge(Attraction first, Attraction second) {
        translateObjectMerger.merge(first.getTitle(), second.getTitle());

        // first.setTitle( translateObjectMerger.merge(first.getTitle(), second.getTitle()) );
        // first.setTags( tagAbstractNsiMerger.merge(first.getTags(), second.getTags()) );
        // first.setTypes( typeAbstractNsiMerger.merge(first.getTypes(), second.getTypes()) );

        first.getImages().clear();
        first.getImages().addAll( second.getImages() );

        List<Product> products = productMerger.merge(first.getProducts(), second.getProducts());

        first.getProducts().clear();
        first.getProducts().addAll( products );

        return first;
    }
}
