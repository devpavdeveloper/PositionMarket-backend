package by.psu.service.api;

import by.psu.exceptions.EntityNotFoundException;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import by.psu.model.postgres.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ProductService extends AbstractService<Product> {

    @Autowired
    private TypeServiceService typeServiceService;

    private final RepositoryProduct repositoryProduct;

    public ProductService(RepositoryProduct repositoryProduct) {
        super(repositoryProduct, Product.class);
        this.repositoryProduct = repositoryProduct;
    }

    @Override
    public void delete(UUID id) {
        Optional<Product> product = repositoryProduct.findById(id);
        if (product.isPresent()) {
            product.get().setPosition(null);
            product.get().setService(null);
            repositoryProduct.saveAndFlush(product.get());
            repositoryProduct.deleteById(id);
        }
    }

    @Transactional
    public List<Product> place(List<Product> newProducts) {
        if (isNull(newProducts) || newProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return newProducts.stream()
                .peek(this::isValidProduct)
                .distinct()
                .collect(Collectors.toList());
    }


    public Product isValidProduct(Product product) {
        if (product.getService() == null) {
            throw new RuntimeException("Product not supported null service");
        }

        if (product.getPrice() == null || product.getPrice().intValue() < 0) {
            throw new RuntimeException("Product not supported price value [" +
                    (product.getPrice() == null ? "null" : product.getPrice().intValue()) + "]");
        }

        if (product.getService().getId() == null) {
            Optional<TypeService> typeService = typeServiceService.isExists(product.getService());
            TypeService service = typeService
                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    String.format(
                                            "Product [%s] not supported service [%s]",
                                            product.getClass(),
                                            product.getService().getId()
                                    )
                            )
                    );
            product.setService(service);
        }

        product.setService(typeServiceService.getOne(product.getService().getId()));

        return product;
    }

}
