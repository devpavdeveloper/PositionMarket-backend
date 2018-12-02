package by.psu.service.api2;


import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Type;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ServiceAttraction {

   /* private final RepositoryAttraction repositoryAttraction;
    private final RepositoryType repositoryType;
    private final RepositoryTag repositoryTag;
    private final RepositoryProduct repositoryProduct;

    @Autowired
    public ServiceAttraction(RepositoryAttraction repositoryAttraction, RepositoryType repositoryType,
                             RepositoryTag repositoryTag, RepositoryProduct repositoryProduct) {
        this.repositoryAttraction = repositoryAttraction;
        this.repositoryType = repositoryType;
        this.repositoryTag = repositoryTag;
        this.repositoryProduct = repositoryProduct;
    }

    @Transactional
    public Attraction getAttractionByUUID(@NotNull UUID uuid) {
        return repositoryAttraction.findById(uuid).orElse(null);
    }

    @Transactional
    public List<Attraction> getAllAttraction() {
        return repositoryAttraction.findAll();
    }

    @Transactional(noRollbackFor = Exception.class)
    public Attraction saveOrUpdate(@NotNull Attraction attraction) {
        placeType(attraction);
        placeTag(attraction);
        placeProduct(attraction);
        return repositoryAttraction.save(attraction);
    }

    private void placeProduct(Attraction attraction) {
        Attraction find = null;
        if (nonNull(attraction.getId())) {
            find = repositoryAttraction.findById(attraction.getId()).orElse(null);
        }

        List<Product> products = null;

        if (nonNull(find)) {
            products = find.getProducts();
        }

        if (nonNull(products) && !products.isEmpty()) {
            Map<TypeService, Product> map =
                    products.stream()
                            .collect(Collectors.toMap(Product::getService, e -> e));
            attraction.setProducts(attraction.getProducts().stream().map(product -> {
                Product productFound = map.get(product.getService());
                if (nonNull(productFound)){
                    productFound.setPrice(product.getPrice());
                    return productFound;
                }
                return product;
            }).collect(Collectors.toList()));
        }
    }

    private void placeTag(Attraction attraction) {
        if (nonNull(attraction) && nonNull(attraction.getTags())) {
            attraction.setTags(
                    attraction.getTags().stream().map(tag -> {
                        Tag tagFound = repositoryTag.findByTitle(tag.getTitle());
                        if (isNull(tagFound)) {
                            tag.setTitle(tag.getTitle().trim());
                            return repositoryTag.save(tag);
                        }
                        return tagFound;
                    }).collect(Collectors.toList())
            );
        }
    }

    private void placeType(Attraction attraction) {
        if (nonNull(attraction) && nonNull(attraction.getTypes())) {
            attraction.setTypes(
                    attraction.getTypes().stream().map(type -> {
                        Type typeFind = repositoryType.findByTitle(type.getTitle());
                        if (isNull(typeFind)) {
                            type.setTitle(type.getTitle().trim());
                            return repositoryType.save(type);
                        }
                        return typeFind;
                    }).collect(Collectors.toList())
            );
        }
    }*/
}
