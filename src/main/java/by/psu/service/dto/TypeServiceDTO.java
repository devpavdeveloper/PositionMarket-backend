package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TypeServiceDTO extends NsiDTO {

    private List<StringValueDTO> description;
    private String type;

}
