package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class AttractionDTO extends AbstractDTO {

    private UUID id;
    private List<StringValueDTO> title;
    private List<StringValueDTO> description;
    private List<PositionImageDTO> images;
    private BigDecimal rentPriceHour;

    private List<UUID> tags;
    private List<UUID> types;

    private List<ProductDTO> products;

}
