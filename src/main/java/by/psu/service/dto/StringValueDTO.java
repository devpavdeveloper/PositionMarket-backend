package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class StringValueDTO extends AbstractDTO {

    private String value;
    private UUID language;

}
