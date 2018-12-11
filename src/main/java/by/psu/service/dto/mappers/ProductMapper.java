package by.psu.service.dto.mappers;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import by.psu.service.dto.mappers.util.MappingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", uses = MappingUtil.class)
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


    @Mapping(source = "service", target = "type", qualifiedBy = MappingUtil.TypeServiceConverter.class)
    ProductDTO to(Product nsi);

    Product from(ProductDTO nsi);
}
