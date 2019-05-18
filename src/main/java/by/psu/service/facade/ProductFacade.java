package by.psu.service.facade;

import by.psu.model.postgres.Product;
import by.psu.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductFacade implements Facade<Product, UUID> {

    private final ProductService productService;

    @Autowired
    public ProductFacade(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public List<Product> getAll() {
        return productService.getAll();
    }

    @Override
    public Optional<Product> getOne(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> save(Product object) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> update(Product object) {
        return Optional.empty();
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public void delete(List<UUID> uuids) {

    }

}
