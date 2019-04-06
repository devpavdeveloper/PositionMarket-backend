package by.psu.service.dto.mappers;

import by.psu.model.postgres.PositionImage;
import by.psu.service.dto.PositionImageDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface PositionImageMapper {

    @Mapping(source = "attraction.id", target = "position")
    @Mapping(source = "image.id", target = "image")
    PositionImageDTO to(PositionImage nsi);

    @InheritInverseConfiguration
    PositionImage from(PositionImageDTO nsi);

}
