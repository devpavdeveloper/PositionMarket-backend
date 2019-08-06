package by.psu.facade;

import by.psu.mappers.AttractionMapper;
import by.psu.model.postgres.Attraction;
import by.psu.service.api.AttractionService;
import by.psu.service.dto.AttractionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttractionFacade extends AbstractFacade<Attraction, AttractionDTO> {

    private final AttractionService attractionService;
    private final AttractionMapper attractionMapper;

    @Autowired
    public AttractionFacade(AttractionService attractionService, AttractionMapper attractionMapper) {
        super(attractionService, attractionMapper);
        this.attractionService = attractionService;
        this.attractionMapper = attractionMapper;
    }

    public AttractionDTO getOne(UUID uuid) {
        return attractionMapper.map(attractionService.findById(uuid));
    }

    public List<AttractionDTO> getAll(Pageable pageable) {
        return attractionService.findAll(pageable).stream().map(
                attractionMapper::map
        ).collect(Collectors.toList());
    }

}
