package by.psu.service.api;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.repository.RepositoryAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ServiceAttraction {


    @Autowired
    private RepositoryAttraction repositoryAttraction;


    public List<Attraction> getAll() {
        return repositoryAttraction.findAll();
    }

    public Attraction getOne(UUID uuid) {
        return repositoryAttraction.getOne(uuid);
    }

    @Transactional
    public Attraction update(Attraction attraction) {
        return repositoryAttraction.save(attraction);
    }

    @Transactional
    public Attraction save(Attraction attraction) {
        return repositoryAttraction.save(attraction);
    }
}
