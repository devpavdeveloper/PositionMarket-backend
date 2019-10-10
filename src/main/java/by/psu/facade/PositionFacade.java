package by.psu.facade;

import by.psu.mappers.PositionMapper;
import by.psu.model.postgres.Position;
import by.psu.service.api.PositionService;
import by.psu.service.dto.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionFacade extends AbstractFacade<Position, PositionDTO> {

    @Autowired
    public PositionFacade(PositionService positionService,
                          PositionMapper positionMapper) {
        super(positionService, positionMapper);
    }

}
