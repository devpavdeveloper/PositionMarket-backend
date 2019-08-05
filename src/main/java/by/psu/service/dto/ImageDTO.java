package by.psu.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageDTO extends AbstractDTO {

    private UUID id;
    private String url;

}
