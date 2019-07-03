package by.psu.facade;

import by.psu.mappers.nsi.NsiMapper;
import by.psu.model.postgres.Type;
import by.psu.service.api.TypeService;
import by.psu.service.dto.TypeDTO;
import org.springframework.stereotype.Service;

@Service
public class TypeFacade extends NsiFacade<Type, TypeDTO> {

    public TypeFacade(TypeService typeService, NsiMapper<Type, TypeDTO> mapper) {
        super(typeService, mapper);
    }

}
