package by.psu.service.dto.mappers;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NsiMapper {
    NsiMapper INSTANCE = Mappers.getMapper( NsiMapper.class );

    @Mapping(source = "title.values", target = "values")
    NsiDTO to(Nsi nsi);

    @Mapping(source = "values", target = "title.values")
    Nsi from(NsiDTO nsi);
}
