package by.psu.service.dto.mappers.util;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.ProductDTO;
import by.psu.service.dto.mappers.ProductMapper;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Collectors;

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

    @TypeServiceConverter
    public NsiDTO typeServiceConverter(TypeService typeService) {
        return TypeServiceNsiMapper.INSTANCE.to(typeService);
    }


}
