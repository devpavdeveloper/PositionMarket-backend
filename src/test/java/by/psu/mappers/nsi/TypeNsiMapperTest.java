package by.psu.mappers.nsi;

import by.psu.model.factory.FactoryType;
import by.psu.model.postgres.Type;
import by.psu.service.dto.TypeDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeNsiMapperTest extends MapperNsiTest<Type, TypeDTO> {

    @Autowired
    private FactoryType factoryType;
    @Autowired
    private TypeNsiMapper typeNsiMapper;

    @Override
    protected void init() {
        super.factoryNsi = factoryType;
        super.mapper = typeNsiMapper;
    }
}
