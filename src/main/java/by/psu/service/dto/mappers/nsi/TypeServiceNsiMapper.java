package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeServiceNsiMapper extends NsiMapper<TypeService, TypeServiceDTO> {}
