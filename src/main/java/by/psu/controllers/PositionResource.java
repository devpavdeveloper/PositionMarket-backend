package by.psu.controllers;

import by.psu.facade.PositionFacade;
import by.psu.model.postgres.Position;
import by.psu.service.dto.PositionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/positions")
public class PositionResource extends AbstractResource<PositionDTO> {

    public PositionResource(PositionFacade positionFacade) {
        super(positionFacade, Position.class);
    }


    @Override
    public ResponseEntity<PositionDTO> create(@RequestBody PositionDTO obj) {
        return super.create(obj);
    }

    @Override
    public ResponseEntity<PositionDTO> update(@RequestBody PositionDTO obj) {
        return super.update(obj);
    }

}
