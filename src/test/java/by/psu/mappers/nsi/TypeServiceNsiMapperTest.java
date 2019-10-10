package by.psu.mappers.nsi;

import by.psu.model.factory.FactoryTypeService;
import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class TypeServiceNsiMapperTest extends MapperNsiTest<TypeService, TypeServiceDTO> {

    @Autowired
    private FactoryTypeService factoryTypeService;

    @Autowired
    private TypeServiceNsiMapper typeServiceNsiMapper;


    @Override
    protected void init() {
        super.mapper = typeServiceNsiMapper;
        super.factoryNsi = factoryTypeService;
    }

}
