package by.psu.service.dto.mappers;

import by.psu.model.postgres.Type;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeNsiMapper extends NsiMapper<Type, NsiDTO> {
    TypeNsiMapper INSTANCE = Mappers.getMapper(TypeNsiMapper.class);
}
