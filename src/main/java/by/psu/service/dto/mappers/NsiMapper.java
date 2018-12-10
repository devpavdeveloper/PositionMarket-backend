package by.psu.service.dto.mappers;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.Mapping;

public interface NsiMapper <T extends Nsi, DTO extends NsiDTO> {

    @Mapping(source = "title.values", target = "values")
    DTO to(T nsi);

    @Mapping(source = "values", target = "title.values")
    T from(DTO nsi);

}
