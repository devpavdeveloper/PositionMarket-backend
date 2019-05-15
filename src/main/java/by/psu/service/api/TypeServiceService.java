package by.psu.service.api;

import by.psu.model.postgres.TypeService;
import by.psu.model.postgres.repository.RepositoryServiceType;
import by.psu.service.merger.AbstractNsiMerger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceService extends NsiService<TypeService> {

    @Autowired
    public TypeServiceService(RepositoryServiceType repositoryServiceType, AbstractNsiMerger<TypeService> abstractNsiMerger) {
        super(repositoryServiceType, abstractNsiMerger);
    }

    @Override
    protected void deleteConsumer(TypeService object) {
        object.setProducts(null);
    }

}
