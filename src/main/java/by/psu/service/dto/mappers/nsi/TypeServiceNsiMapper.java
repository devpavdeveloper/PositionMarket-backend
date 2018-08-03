package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.TypeService;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.MapperConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface TypeServiceNsiMapper extends NsiMapper<TypeService, TypeServiceDTO> {}
