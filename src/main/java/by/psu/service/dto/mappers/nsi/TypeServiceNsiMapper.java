package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.MapperConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface TypeServiceNsiMapper extends NsiMapper<TypeService, TypeServiceDTO> {

    @Override
    @Mapping(source = "title.values", target = "values")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "description.values", target = "description")
    TypeServiceDTO map(TypeService nsi);

    @Override
    @InheritInverseConfiguration
    TypeService map(TypeServiceDTO nsi);

}
