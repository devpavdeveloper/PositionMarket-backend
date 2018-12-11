package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.TypeService;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeServiceNsiMapper extends NsiMapper<TypeService, NsiDTO> {
    TypeServiceNsiMapper INSTANCE = Mappers.getMapper(TypeServiceNsiMapper.class);
}
