package by.psu.facade;

import by.psu.mappers.nsi.TagNsiMapper;
import by.psu.model.postgres.Tag;
import by.psu.service.api.TagService;
import by.psu.service.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagFacade extends NsiFacade<Tag, TagDTO> {

    @Autowired
    public TagFacade(TagService tagService, TagNsiMapper mapper) {
        super(tagService, mapper);
    }

}
