package by.psu.service.facade;

import by.psu.model.postgres.Tag;
import by.psu.service.api.NsiService;
import by.psu.service.dto.TagDTO;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TagFacade extends NsiFacade<Tag, TagDTO> {

    public TagFacade(NsiService<Tag> nsiService, TagNsiMapper mapper) {
        super(nsiService, mapper);
    }

}
