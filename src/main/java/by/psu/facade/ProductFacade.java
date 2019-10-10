package by.psu.facade;

import by.psu.mappers.ProductMapper;
import by.psu.model.postgres.Product;
import by.psu.service.api.ProductService;
import by.psu.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductFacade extends AbstractFacade<Product, ProductDTO> {

    @Autowired
    public ProductFacade(ProductService productService, ProductMapper productMapper) {
        super(productService, productMapper);
    }

    protected ProductDTO updatePlace(Product oldEntity, Product newEntity) {
        return null;
    }
}
