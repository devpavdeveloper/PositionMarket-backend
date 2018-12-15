package by.psu.service.facade;

import by.psu.model.postgres.TypeService;
import by.psu.service.api.NsiService;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceFacade extends NsiFacade<TypeService, TypeServiceDTO> {

    public TypeServiceFacade(NsiService<TypeService> nsiService, TypeServiceNsiMapper mapper) {
        super(nsiService, mapper);
    }
}
