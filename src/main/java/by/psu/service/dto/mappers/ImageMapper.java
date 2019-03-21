package by.psu.service.dto.mappers;

import by.psu.model.postgres.Image;
import by.psu.service.dto.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
public interface ImageMapper {

    ImageDTO to(Image nsi);

    Image from(ImageDTO nsi);

}
