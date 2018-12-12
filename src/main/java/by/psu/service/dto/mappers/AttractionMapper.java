package by.psu.service.dto.mappers;

import by.psu.model.postgres.Attraction;
import by.psu.service.dto.AttractionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = ProductMapper.class)
public interface AttractionMapper {

    AttractionMapper INSTANCE = Mappers.getMapper(AttractionMapper.class);

    @Mapping(source = "title.values", target = "values")
    AttractionDTO to(Attraction nsi);

    @Mapping(source = "values", target = "title.values")
    Attraction from(AttractionDTO nsi);

}
