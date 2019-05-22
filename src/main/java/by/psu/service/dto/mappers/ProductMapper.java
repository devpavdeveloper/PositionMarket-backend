package by.psu.service.dto.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface ProductMapper {

    @Mapping(source = "service.id", target = "service")
    @Mapping(source = "order", target = "order")
    ProductDTO map(Product nsi);

    @Mapping(source = "service", target = "service.id")
    Product map(ProductDTO nsi);

}
