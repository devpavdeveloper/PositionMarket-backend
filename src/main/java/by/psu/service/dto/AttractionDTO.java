package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class AttractionDTO extends AbstractDTO {

    private List<StringValueDTO> title;
    private List<StringValueDTO> description;
    private List<PositionImageDTO> images;

    private List<UUID> tags;
    private List<UUID> types;

    private List<ProductDTO> products;

}
