package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter @Setter
public class AttractionDTO {

    private UUID id;
    private List<StringValueDTO> title;
    private String link;
    private String image;

    private List<TagDTO> tags;
    private List<TypeDTO> types;

    private List<ProductDTO> products;

}
