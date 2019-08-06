package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PositionImageDTO extends AbstractDTO {

    private UUID image;
    private String url;
    private boolean mainImage;

}
