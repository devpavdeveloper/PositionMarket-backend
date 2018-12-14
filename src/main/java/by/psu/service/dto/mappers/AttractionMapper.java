package by.psu.service.dto.mappers;

import by.psu.model.postgres.Attraction;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import by.psu.service.dto.mappers.nsi.TypeNsiMapper;
import by.psu.service.dto.mappers.util.MappingUtil;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = {
        ProductMapper.class,
        TypeNsiMapper.class,
        TagNsiMapper.class
})
public interface AttractionMapper {

    @Mappings(value = {
            @Mapping(source = "title.values", target = "title"),
            @Mapping(source = "linkSource", target = "link")
    })
    AttractionDTO to(Attraction nsi);

    @InheritInverseConfiguration
    Attraction from(AttractionDTO nsi);

}
