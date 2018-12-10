package by.psu.service.facade;

import by.psu.model.postgres.Tag;
import by.psu.service.api2.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.NsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TagFacade extends NsiFacade<Tag, NsiDTO> {

    public TagFacade(ServiceNsi<Tag> serviceNsi, NsiMapper<Tag, NsiDTO> mapper) {
        super(serviceNsi, mapper);
    }

}
