package by.psu.mappers.nsi;

import by.psu.mappers.MapperConfiguration;
import by.psu.model.postgres.Type;
import by.psu.service.dto.TypeDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface TypeNsiMapper extends NsiMapper<Type, TypeDTO> {}
