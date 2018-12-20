package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Tag;
import by.psu.service.dto.TagDTO;
import by.psu.service.dto.mappers.MapperConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface TagNsiMapper extends NsiMapper<Tag, TagDTO> { }
