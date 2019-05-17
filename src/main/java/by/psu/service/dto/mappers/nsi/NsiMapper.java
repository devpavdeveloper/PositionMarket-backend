package by.psu.service.dto.mappers.nsi;

import by.psu.model.postgres.Nsi;
import by.psu.service.dto.NsiDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;


public interface NsiMapper <T extends Nsi, DTO extends NsiDTO> {

    @Mapping(source = "title.values", target = "values")
    DTO map(T nsi);

    @InheritInverseConfiguration
    T map(DTO nsi);

}
