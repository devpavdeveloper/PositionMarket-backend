package by.psu.service.dto.mappers.util;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import by.psu.service.dto.mappers.ProductMapper;
import org.mapstruct.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MappingUtil {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProductConverter {
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface TypeServiceConverter {
    }

    @ProductConverter
    public List<ProductDTO> productConverter(List<Product> products) {
        return products.stream().map(ProductMapper.INSTANCE::to).collect(Collectors.toList());
    }

}
