package by.psu.facade;

import by.psu.mappers.AttractionMapper;
import by.psu.model.postgres.Attraction;
import by.psu.service.api.AttractionService;
import by.psu.service.dto.AttractionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttractionFacade extends AbstractFacade<Attraction, AttractionDTO> {

    @Autowired
    public AttractionFacade(AttractionService attractionService,
                            AttractionMapper attractionMapper) {
        super(attractionService, attractionMapper);
    }

}
