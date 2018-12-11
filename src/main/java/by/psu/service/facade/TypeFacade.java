package by.psu.service.facade;

import by.psu.model.postgres.Type;
import by.psu.service.api2.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeFacade extends NsiFacade<Type, NsiDTO> {

    public TypeFacade(ServiceNsi<Type> serviceNsi, NsiMapper<Type, NsiDTO> mapper) {
        super(serviceNsi, mapper);
    }
}
