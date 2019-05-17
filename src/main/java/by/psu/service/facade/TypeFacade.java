package by.psu.service.facade;

import by.psu.model.postgres.Type;
import by.psu.service.api.TypeService;
import by.psu.service.dto.TypeDTO;
import by.psu.service.dto.mappers.nsi.NsiMapper;
import org.springframework.stereotype.Service;

@Service
public class TypeFacade extends NsiFacade<Type, TypeDTO> {

    public TypeFacade(TypeService typeService, NsiMapper<Type, TypeDTO> mapper) {
        super(typeService, mapper);
    }

}
