package by.psu.mappers.nsi;

import by.psu.mappers.MapperConfiguration;
import by.psu.model.postgres.Tag;
import by.psu.service.dto.TagDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface TagNsiMapper extends NsiMapper<Tag, TagDTO> { }
