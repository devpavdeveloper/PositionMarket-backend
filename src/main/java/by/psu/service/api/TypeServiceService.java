package by.psu.service.api;

import by.psu.model.postgres.BasicEntity;
import by.psu.model.postgres.TypeService;
import by.psu.model.postgres.repository.RepositoryServiceType;
import by.psu.service.merger.AbstractNsiMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class TypeServiceService extends NsiService<TypeService> {

    @Autowired
    private ProductService productService;


    public TypeServiceService(RepositoryServiceType repositoryServiceType,
                              AbstractNsiMerger<TypeService> abstractNsiMerger) {
        super(repositoryServiceType, abstractNsiMerger);
    }

    @Override
    protected void deleteConsumer(TypeService object) {
        if (nonNull(object.getProducts()) && !object.getProducts().isEmpty()) {
            Set<UUID> productUUIDS = object.getProducts().stream()
                    .map(BasicEntity::getId)
                    .collect(Collectors.toSet());

            productService.delete(productUUIDS);

            object.getProducts().clear();
        }

    }

}
