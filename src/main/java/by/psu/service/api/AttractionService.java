package by.psu.service.api;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.repository.RepositoryAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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


    public List<Attraction> getAll() {
        return repositoryAttraction.findAll();
    }

    public Attraction getOne(UUID uuid) {
        return repositoryAttraction.getOne(uuid);
    }

    @Transactional
    public Attraction update(Attraction attraction) {
        return save(attraction);
    }

    @Transactional
    public Attraction save(Attraction attraction) {
        attraction.setTypes(attraction.getTypes().stream().map(serviceType::save).collect(Collectors.toList()));
        attraction.setTags(attraction.getTags().stream().map(serviceTag::save).collect(Collectors.toList()));

        attraction.setProducts(productService.place(attraction.getProducts()));

        return repositoryAttraction.save(attraction);
    }
}
