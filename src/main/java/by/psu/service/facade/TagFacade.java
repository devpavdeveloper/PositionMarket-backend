package by.psu.service.facade;

import by.psu.model.postgres.Tag;
import by.psu.service.api.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.TagDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TagFacade extends NsiFacade<Tag, TagDTO> {

    public TagFacade(ServiceNsi<Tag> serviceNsi, TagNsiMapper mapper) {
        super(serviceNsi, mapper);
    }

}
