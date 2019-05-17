package by.psu.service.facade;

import by.psu.model.postgres.TypeService;
import by.psu.service.api.TypeServiceService;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceFacade extends NsiFacade<TypeService, TypeServiceDTO> {

    public TypeServiceFacade(TypeServiceService typeServiceService, TypeServiceNsiMapper mapper) {
        super(typeServiceService, mapper);
    }

}
