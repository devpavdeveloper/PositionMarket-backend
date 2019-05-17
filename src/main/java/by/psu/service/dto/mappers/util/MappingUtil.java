package by.psu.service.dto.mappers.util;

import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import by.psu.service.dto.mappers.ProductMapper;
import org.mapstruct.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingUtil {

    @Autowired
    private ProductMapper productMapper;

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface ProductConverter {
    }

    @ProductConverter
    public List<ProductDTO> productConverter(List<Product> products) {
        return products.stream()
                .map(product -> productMapper.map(product))
                .collect(Collectors.toList());
    }

}
