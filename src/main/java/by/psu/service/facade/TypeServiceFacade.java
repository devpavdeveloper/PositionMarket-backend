package by.psu.service.facade;

import by.psu.model.postgres.TypeService;
import by.psu.service.api2.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.NsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceFacade extends NsiFacade<TypeService, NsiDTO> {

    public TypeServiceFacade(ServiceNsi<TypeService> serviceNsi, NsiMapper<TypeService, NsiDTO> mapper) {
        super(serviceNsi, mapper);
    }
}
