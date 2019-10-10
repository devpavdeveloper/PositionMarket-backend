package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class NsiDTO extends AbstractDTO {

    private List<StringValueDTO> values = new ArrayList<>();

}
