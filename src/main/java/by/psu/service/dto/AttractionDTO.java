package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AttractionDTO {

    private List<StringValueDTO> values;
    private String link;
    private String image;

    private List<NsiDTO> tags;
    private List<NsiDTO> types;

    private List<ProductDTO> products;

}
