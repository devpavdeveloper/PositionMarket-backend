package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class NsiDTO {

    private UUID id;
    private List<StringValueDTO> values = new ArrayList<>();
}
