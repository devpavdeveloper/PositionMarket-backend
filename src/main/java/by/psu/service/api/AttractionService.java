package by.psu.service.api;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.PositionImage;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.repository.RepositoryAttraction;
import by.psu.service.merger.AttractionMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class AttractionService {

    @Autowired
    private RepositoryAttraction repositoryAttraction;
    @Autowired
    private TagService serviceTag;
    @Autowired
    private TypeService serviceType;
    @Autowired
    private ProductService productService;
    @Autowired
    private AttractionMerger attractionMerger;
    @Autowired
    private PositionImageService positionImageService;
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional(readOnly = true)
    public Page<Attraction> getAll(Pageable pageable) {
        return repositoryAttraction.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Attraction getOne(UUID uuid) {
        return repositoryAttraction.findById(uuid).orElse(null);
    }

    @Transactional
    public Attraction update(Attraction attraction) {
        if ( attraction == null ) {
            throw new RuntimeException("Attraction entity is null");
        }
        if ( attraction.getId() == null ) {
            throw new RuntimeException("Attraction id is null");
        }
        Attraction findEntityById = getOne(attraction.getId());
        if ( nonNull(attraction.getTags()) ) {
            findEntityById.setTags(attraction.getTags());
        }
        if ( nonNull(attraction.getTypes()) ) {
            findEntityById.setTypes(attraction.getTypes());
        }
        return repositoryAttraction.save( attractionMerger.merge(findEntityById, attraction) );
    }

    @Transactional
    public Attraction save(Attraction attraction) {

        if ( attraction == null ) {
            throw new RuntimeException("Attraction entity is null");
        }

        if ( attraction.getId() != null ) {
            throw new RuntimeException("Attraction id isn't null");
        }

        attraction.setTypes(attraction.getTypes().stream()
                .map(serviceType::save)
                .collect(Collectors.toList()));

        attraction.setTags(attraction.getTags().stream()
                .map(serviceTag::save)
                .collect(Collectors.toList()));

        List<PositionImage> positionImages = attraction.getImages().stream()
                .map(positionImageService::save)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        attraction.getImages().clear();
        attraction.getImages().addAll(positionImages);

        List<Product> products = productService.place(attraction.getProducts());

        attraction.getProducts().clear();
        attraction.getProducts().addAll(products);

        return repositoryAttraction.save(attraction);
    }

    @Transactional
    public void delete(UUID uuid) {
        Optional<Attraction> optionalAttraction = repositoryAttraction.findById(uuid);

        if ( optionalAttraction.isPresent() ) {
            Attraction attraction = optionalAttraction.get();
            attraction.setTypes(null);
            attraction.setTags(null);
            entityManager.merge(attraction);
            entityManager.flush();
            entityManager.clear();
            repositoryAttraction.delete(attraction);
        }

    }
}
