package by.psu.service.dto.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(source = "service.title.values", target = "service.values")
    ProductDTO to(Product nsi);

    @InheritInverseConfiguration
    Product from(ProductDTO nsi);
}
