package by.psu.controllers;

import by.psu.facade.AttractionFacade;
import by.psu.model.postgres.Attraction;
import by.psu.service.dto.AttractionDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/attractions")
public class PositionController extends AbstractResource<AttractionDTO> {

    public PositionController(AttractionFacade attractionFacade) {
        super(attractionFacade, Attraction.class);
    }


}
