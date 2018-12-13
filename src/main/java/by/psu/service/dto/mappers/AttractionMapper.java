package by.psu.service.dto.mappers;

import by.psu.model.postgres.Attraction;
import by.psu.service.dto.AttractionDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface AttractionMapper {

    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);
    @Mappings({
            @Mapping(source = "title.values", target = "title"),
            @Mapping(source = "linkSource", target = "link"),
            @Mapping(source = "tags", target = "tags")
    })
    AttractionDTO to(Attraction nsi);

    @InheritInverseConfiguration
    Attraction from(AttractionDTO nsi);

}
