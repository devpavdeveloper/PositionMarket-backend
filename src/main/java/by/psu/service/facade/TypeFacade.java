package by.psu.service.facade;

import by.psu.model.postgres.Type;
import by.psu.service.api.NsiService;
import by.psu.service.dto.TypeDTO;
import by.psu.service.dto.mappers.nsi.TypeNsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeFacade extends NsiFacade<Type, TypeDTO> {

    public TypeFacade(NsiService<Type> nsiService, TypeNsiMapper mapper) {
        super(nsiService, mapper);
    }
}
