package by.psu.service.api;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.merger.AttractionMerger;
import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.Tag;
import by.psu.model.postgres.Type;
import by.psu.model.postgres.repository.RepositoryAttraction;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class AttractionService extends AbstractService<Attraction> {

    private final TypeService typeService;
    private final TagService tagService;

    private final ProductService productService;
    private final AttractionMerger attractionMerger;

    public AttractionService(RepositoryAttraction abstractRepository,
                             TypeService typeService,
                             TagService tagService,
                             ProductService productService,
                             AttractionMerger attractionMerger) {
        super(abstractRepository, Attraction.class);
        this.typeService = typeService;
        this.tagService = tagService;
        this.productService = productService;
        this.attractionMerger = attractionMerger;
    }

    @Override
    @Transactional
    public Attraction update(Attraction object) {
        isValidUpdateObject(object);

        Attraction attraction = findById(object.getId());

        if (isNull(attraction)) {
            throw new EntityNotFoundException("Entity not found with ID [" + object.getId() + "]");
        }

        attractionMerger.merge(attraction, object);

        setTagsInPosition(attraction, object.getTags());
        setTypesInPosition(attraction, object.getTypes());

        return super.update(attraction);
    }

    @Override
    @Transactional
    public Attraction save(Attraction object) {
        isValidSaveObject(object);

        setTagsInPosition(object, object.getTags());
        setTypesInPosition(object, object.getTypes());

        List<Product> placedProducts = productService.place(object.getProducts());
        object.setProducts(placedProducts);

        return super.save(object);
    }

    private void setTagsInPosition(@NonNull final Attraction position,
                         final List<Tag> tags) {
        position.setTags(tagService.getReferencesByEntities(tags));
    }

    private void setTypesInPosition(@NonNull final Attraction position,
                          final List<Type> types) {
        position.setTypes(typeService.getReferencesByEntities(types));
    }
}
