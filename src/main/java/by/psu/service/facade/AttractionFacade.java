package by.psu.service.facade;

import by.psu.service.api.AttractionService;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.AttractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttractionFacade {

    private final AttractionService attractionService;
    private final AttractionMapper attractionMapper;

    @Autowired
    public AttractionFacade(AttractionService attractionService, AttractionMapper attractionMapper) {
        this.attractionService = attractionService;
        this.attractionMapper = attractionMapper;
    }

    @Transactional(readOnly = true)
    public List<AttractionDTO> getAll() {
        return attractionService.getAll().stream().map(
                attractionMapper::to
        ).collect(Collectors.toList());
    }

    @Transactional
    public AttractionDTO save(AttractionDTO attractionDTO) {
        return attractionMapper.to(
                attractionService.save(
                        attractionMapper.from(attractionDTO)
                )
        );
    }

}
