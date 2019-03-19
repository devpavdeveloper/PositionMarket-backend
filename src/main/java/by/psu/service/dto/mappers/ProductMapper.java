package by.psu.service.dto.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(source = "service.id", target = "service")
    ProductDTO to(Product nsi);

    @Mapping(source = "service", target = "service.id")
    Product from(ProductDTO nsi);

}
