package by.psu.controllers;

import by.psu.model.postgres.Product;
import by.psu.service.facade.ProductFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController implements Controller<Product, UUID> {

    private final ProductFacade productFacade;

    @Autowired
    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<Product>> get() {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable("id") UUID id) {
        return null;
    }

    @Override
    @PostMapping()
    public ResponseEntity<Product> create(Product obj) {
        return null;
    }

    @Override
    @PutMapping()
    public ResponseEntity<Product> update(Product obj) {
        return null;
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") UUID uuid) {
        return null;
    }

    @Override
    public ResponseEntity delete(UUID[] uuid) {
        return null;
    }

}
