package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PositionImageDTO {

    private UUID image;
    private UUID position;
    private boolean mainImage;

}
