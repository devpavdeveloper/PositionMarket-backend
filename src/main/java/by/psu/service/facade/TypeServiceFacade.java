package by.psu.service.facade;

import by.psu.model.postgres.TypeService;
import by.psu.service.api.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceFacade extends NsiFacade<TypeService, TypeServiceDTO> {

    public TypeServiceFacade(ServiceNsi<TypeService> serviceNsi, TypeServiceNsiMapper mapper) {
        super(serviceNsi, mapper);
    }
}
