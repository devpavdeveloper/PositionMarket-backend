package by.psu.facade;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.mappers.ProductMapper;
import by.psu.model.postgres.Product;
import by.psu.service.api.ProductService;
import by.psu.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductFacade implements Facade<ProductDTO, UUID> {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductFacade(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAll() {
        return productService.getAll().stream()
                .map(productMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getOne(UUID uuid) {
        Product product = productService.getOne(uuid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Product with uuid: [%s]", uuid)));
        return productMapper.map(product);
    }

    @Override
    public ProductDTO save(Product object) {
        return productService.save(object).map(productMapper::map).orElse(null);
    }

    @Override
    public ProductDTO update(Product object) {
        return productService.save(object).map(productMapper::map).orElse(null);
    }

    @Override
    public void delete(UUID uuid) {
        productService.delete(uuid);
    }

    @Override
    public void delete(List<UUID> uuids) {
        productService.delete(uuids);
    }

}
