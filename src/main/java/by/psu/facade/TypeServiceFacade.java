package by.psu.facade;

import by.psu.mappers.nsi.TypeServiceNsiMapper;
import by.psu.model.postgres.TypeService;
import by.psu.service.api.TypeServiceService;
import by.psu.service.dto.TypeServiceDTO;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceFacade extends NsiFacade<TypeService, TypeServiceDTO> {

    public TypeServiceFacade(TypeServiceService typeServiceService, TypeServiceNsiMapper mapper) {
        super(typeServiceService, mapper);
    }

}
