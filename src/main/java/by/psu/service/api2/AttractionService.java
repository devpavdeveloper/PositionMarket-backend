package by.psu.service.api2;


import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.repository.RepositoryAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class AttractionService {

    private final RepositoryAttraction repositoryAttraction;


    @Autowired
    public AttractionService(RepositoryAttraction repositoryAttraction) {
        this.repositoryAttraction = repositoryAttraction;
    }


    public Attraction getAttractionByUUID(UUID uuid) {
        return repositoryAttraction.getOne(uuid);
    }

    public List<Attraction> getAllAttraction() {
        return repositoryAttraction.findAll();
    }

    @Transactional(noRollbackFor = Exception.class)
    public Attraction saveOrUpdate(Attraction attraction) {
        return repositoryAttraction.save(attraction);
    }
}
