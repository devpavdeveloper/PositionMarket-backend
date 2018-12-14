package by.psu.controllers.nsi;

import by.psu.model.postgres.Tag;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.TagDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
public class NsiTagController extends NsiController<Tag, TagDTO> { }