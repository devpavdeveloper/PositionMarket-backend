package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Tag;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagNsiMapper extends NsiMapper<Tag, NsiDTO> {
    TagNsiMapper INSTANCE = Mappers.getMapper(TagNsiMapper.class);
}
