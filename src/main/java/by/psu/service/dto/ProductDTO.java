package by.psu.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductDTO {

    private UUID id;
    private BigDecimal price;
    private UUID service;

}
