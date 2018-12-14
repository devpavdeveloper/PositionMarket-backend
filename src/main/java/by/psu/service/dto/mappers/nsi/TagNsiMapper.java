package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Tag;
import by.psu.service.dto.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagNsiMapper extends NsiMapper<Tag, TagDTO> { }
