package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Type;
import by.psu.service.dto.TypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeNsiMapper extends NsiMapper<Type, TypeDTO> {}
