package by.psu.service.api;

import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.repository.AbstractRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttractionService extends AbstractService<Attraction> {

    public AttractionService(AbstractRepository<Attraction> abstractRepository) {
        super(abstractRepository, Attraction.class);
    }

    @Override
    @Transactional
    public Attraction update(Attraction object) {
        return super.update(object);
    }


}
