package by.psu.controllers.nsi;

import by.psu.model.postgres.Tag;
import by.psu.service.dto.TagDTO;
import by.psu.service.facade.TagFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class NsiTagController extends NsiController<Tag, TagDTO> {

    @Autowired
    public NsiTagController(TagFacade tagFacade) {
        super(tagFacade);
    }

}