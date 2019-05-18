package by.psu.service.api;

import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import by.psu.model.postgres.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ProductService implements ServiceCRUD<Product> {

    @Autowired
    private TypeServiceService typeServiceService;
    @Autowired
    private RepositoryProduct repositoryProduct;


    public List<Product> getAll() {
        return repositoryProduct.findAll();
    }

    public Optional<Product> getOne(UUID id) {
        return repositoryProduct.findById(id);
    }

    @Override
    public Optional<Product> save(Product object) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Product> update(Product object) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<Product> product = repositoryProduct.findById(id);
        if (product.isPresent()) {
            product.get().setAttraction(null);
            product.get().setService(null);
            repositoryProduct.saveAndFlush(product.get());
            repositoryProduct.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void delete(Iterable<UUID> ids) {
        if ( isNull(ids) ) {
            return;
        }

        ids.iterator()
                .forEachRemaining(this::delete);
    }

    @Transactional
    public List<Product> place(List<Product> newProducts) {
        if ( newProducts != null && !newProducts.isEmpty() ) {
            return new ArrayList<>(newProducts.stream()
                    .peek(product -> {
                        if (product.getService() == null) {
                            throw new RuntimeException("Product not supported null service");
                        }

                        if ( product.getPrice() == null || product.getPrice().intValue() < 0 ) {
                            throw new RuntimeException("Product not supported price value [" +
                                    (product.getPrice() == null ? "null" : product.getPrice().intValue()) + "]");
                        }

                        if ( product.getService().getId() == null ) {
                            Optional<TypeService> typeService = typeServiceService.isExists(product.getService());
                            TypeService service = typeService.orElseThrow(() -> new RuntimeException("Product not supported null service"));
                            product.setService(service);
                        }

                        product.setService(typeServiceService.getOne(product.getService().getId()));
                    })
                    .collect(Collectors.toMap(key -> key.getService().getId(), value -> value, (value, duplicate) -> value))
                    .values());
        }
        return Collections.emptyList();
    }
}
