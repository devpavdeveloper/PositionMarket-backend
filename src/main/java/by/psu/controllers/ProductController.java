package by.psu.controllers;

import by.psu.facade.ProductFacade;
import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController extends AbstractResource<ProductDTO> {

    public ProductController(ProductFacade productFacade) {
        super(productFacade, Product.class);
    }

}
