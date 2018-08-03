package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Type;
import by.psu.service.dto.TypeDTO;
import by.psu.service.dto.mappers.MapperConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface TypeNsiMapper extends NsiMapper<Type, TypeDTO> {}
