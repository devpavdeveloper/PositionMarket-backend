package by.psu.mappers;

import by.psu.model.postgres.PositionImage;
import by.psu.service.dto.PositionImageDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfiguration.class)
public interface PositionImageMapper extends AbstractMapper<PositionImage, PositionImageDTO> {

    @Mapping(source = "image.id", target = "image")
    @Mapping(source = "image.url", target = "url")
    PositionImageDTO map(PositionImage nsi);

    @InheritInverseConfiguration
    PositionImage map(PositionImageDTO nsi);

}
