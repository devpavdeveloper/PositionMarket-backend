package by.psu.service.api;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.merger.PositionMerger;
import by.psu.model.postgres.Position;
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
public class PositionService extends AbstractService<Position> {

    private final TypeService typeService;
    private final TagService tagService;

    private final ProductService productService;
    private final PositionMerger positionMerger;

    public PositionService(RepositoryAttraction abstractRepository,
                           TypeService typeService,
                           TagService tagService,
                           ProductService productService,
                           PositionMerger positionMerger) {
        super(abstractRepository, Position.class);
        this.typeService = typeService;
        this.tagService = tagService;
        this.productService = productService;
        this.positionMerger = positionMerger;
    }

    @Override
    @Transactional
    public Position update(Position object) {
        isValidUpdateObject(object);

        Position position = findById(object.getId());

        if (isNull(position)) {
            throw new EntityNotFoundException("Entity not found with ID [" + object.getId() + "]");
        }

        positionMerger.merge(position, object);

        setTagsInPosition(position, object.getTags());
        setTypesInPosition(position, object.getTypes());

        return super.update(position);
    }

    @Override
    @Transactional
    public Position save(Position object) {
        isValidSaveObject(object);

        setTagsInPosition(object, object.getTags());
        setTypesInPosition(object, object.getTypes());

        List<Product> placedProducts = productService.place(object.getProducts());
        object.getProducts().clear();

        super.save(object);

        object.getProducts().addAll(placedProducts);

        return object;
    }

    private void setTagsInPosition(@NonNull final Position position,
                         final List<Tag> tags) {
        position.setTags(tagService.getReferencesByEntities(tags));
    }

    private void setTypesInPosition(@NonNull final Position position,
                          final List<Type> types) {
        position.setTypes(typeService.getReferencesByEntities(types));
    }
}
