package by.psu.service.facade;

import by.psu.model.postgres.Type;
import by.psu.service.api.ServiceNsi;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.TypeDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import by.psu.service.dto.mappers.nsi.TypeNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeFacade extends NsiFacade<Type, TypeDTO> {

    public TypeFacade(ServiceNsi<Type> serviceNsi, TypeNsiMapper mapper) {
        super(serviceNsi, mapper);
    }
}
