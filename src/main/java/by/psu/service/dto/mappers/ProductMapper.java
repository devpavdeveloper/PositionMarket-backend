package by.psu.service.dto.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "service", target = "type")
    @Mapping(source = "service.title.values", target = "type.values")
    ProductDTO to(Product nsi);

    @InheritInverseConfiguration
    Product from(ProductDTO nsi);
}
