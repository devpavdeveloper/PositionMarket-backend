package by.psu.service.facade;

import by.psu.exceptions.BadRequestException;
import by.psu.service.api.AttractionService;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.AttractionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

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
    public AttractionDTO getOne(UUID uuid) {
        return attractionMapper.map(attractionService.getOne(uuid));
    }

    @Transactional(readOnly = true)
    public List<AttractionDTO> getAll(Pageable pageable) {
        return attractionService.getAll(pageable).stream().map(
                attractionMapper::map
        ).collect(Collectors.toList());
    }

    @Transactional
    public AttractionDTO update(AttractionDTO attractionDTO) {
        return attractionMapper.map(
                attractionService.update(
                        attractionMapper.map(attractionDTO)
                )
        );
    }

    public AttractionDTO save(AttractionDTO attractionDTO) {
        return attractionMapper.map(
                attractionService.save(
                        attractionMapper.map(attractionDTO)
                )
        );
    }

    @Transactional
    public void delete(List<UUID> uuids) {
        if ( isNull(uuids) ) {
            throw new BadRequestException("List can't to be null");
        }

        uuids.forEach(this::delete);
    }

    @Transactional
    public void delete(UUID uuid) {
        attractionService.delete(uuid);
    }

}
