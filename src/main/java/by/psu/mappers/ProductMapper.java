package by.psu.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper extends AbstractMapper<Product, ProductDTO> {

    @Mapping(source = "service.id", target = "service")
    @Mapping(source = "order", target = "order")
    @Mapping(source = "attraction.id", target = "positionId")
    ProductDTO map(Product nsi);

    @Mapping(source = "service", target = "service.id")
    Product map(ProductDTO nsi);

}
