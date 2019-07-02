package by.psu.services;

import by.psu.BaseTest;
import by.psu.model.factory.FactoryProduct;
import by.psu.model.factory.FactoryTypeService;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import by.psu.model.postgres.TypeServiceEnum;
import by.psu.service.api.ProductService;
import by.psu.service.api.TypeServiceService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.Assert.*;

public class ProductServiceTest extends BaseTest {

    @Autowired
    private FactoryTypeService factoryTypeService;
    @Autowired
    private FactoryProduct factoryProduct;

    @Autowired
    private ProductService productService;
    @Autowired
    private TypeServiceService typeServiceService;

    private Product product;
    private TypeService typeService;


    @Before
    public void setUp() {
        product = factoryProduct.create(43, 0L);

        typeService = factoryTypeService.create("Тип сервиса", "Service type");
        typeService = typeServiceService.save(typeService);

        product.setService(typeService);

        product = productService.save(product).orElse(null);

        assertNotNull(product);
        assertNotNull(productService.isValidProduct(product));

        assertEquals(product.getService().getId(), typeService.getId());
        assertNotNull(product.getId());
    }

    @After
    public void tearDown() {
        if (nonNull(product) && nonNull(product.getId()))
            productService.delete(product.getId());
        if (nonNull(typeService) && nonNull(typeService.getId()))
            typeServiceService.delete(typeService.getId());
    }

    @Test
    @Transactional
    public void testPlaceProductWithValidTypeService() {
        assertNotNull(product);
        assertNotNull(product.getId());
        assertNotNull(productService.isValidProduct(product));
        assertEquals(product.getService().getId(), typeService.getId());
    }

    @Test(expected = RuntimeException.class)
    public void testPlaceProductThrowExceptionTypeServiceIsNull() {
        product.setService(null);
        productService.isValidProduct(product);
    }

    @Test(expected = RuntimeException.class)
    public void testPlaceProductThrowExceptionTypeServiceHavePriceLessZero() {
        product.setPrice(new BigDecimal(-3L));
        productService.isValidProduct(product);
    }

    @Test(expected = RuntimeException.class)
    public void testPlaceProductThrowExceptionTypeServiceHavePriceNull() {
        product.setPrice(null);
        productService.isValidProduct(product);
    }

    @Test
    public void testPlaceProductAddTwoEq() {
        List<Product> products = productService.place(new ArrayList<>(Arrays.asList(product, product, product, product)));

        assertNotNull(products);
        assertFalse(products.isEmpty());
        assertEquals(products.size(), 1);
    }
}
