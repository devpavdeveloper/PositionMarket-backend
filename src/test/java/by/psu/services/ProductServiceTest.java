package by.psu.services;

import by.psu.BaseTest;
import by.psu.model.postgres.Product;
import by.psu.model.postgres.TypeService;
import by.psu.service.api.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest extends BaseTest {

    private List<Product> products;
    private List<TypeService> typeServices;

    @Value(value = "classpath:projects.json")
    private Resource projectJSON;
    @Value(value = "classpath:typeServices.json")
    private Resource typeServicesJSON;

    @Autowired
    private ProductService productService;

    @Before
    public void init() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        typeServices = objectMapper.readValue(typeServicesJSON.getFile(), new TypeReference<List<TypeService>>(){});
        products = objectMapper.readValue(projectJSON.getFile(), new TypeReference<List<Product>>(){});
    }

    @Test
    public void testPlaceProductsWithExistService() {
        TypeService typeService = typeServices.get(2);
        Product product = products.get(0);
        product.setService(typeService);

        List<Product> products = productService.place(Collections.singletonList(product));

        assertNotNull(products);
        assertEquals(products.size(), 1);
    }

    @Test
    public void testPlaceProductThrowExceptionUUIDIsNullTypeService() {
        TypeService typeService = typeServices.get(2);
        typeService.setId(null);
        Product product = products.get(0);
        product.setService(typeService);
        try {
            productService.place(Collections.singletonList(product));
            fail();
        } catch (Exception e) {
               assertTrue(e instanceof RuntimeException);
               assertEquals(e.getMessage(),"Product not supported null service");
        }
    }

    @Test
    public void testPlaceProductThrowExceptionTypeServiceIsNull() {
        Product product = products.get(0);
        product.setService(null);
        try {
            productService.place(Collections.singletonList(product));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
            assertEquals(e.getMessage(),"Product not supported null service");
        }
    }

    @Test
    public void testPlaceProductThrowExceptionTypeServiceHavePriceLessZero() {
        TypeService typeService = typeServices.get(2);
        Product product = products.get(0);
        product.setService(typeService);
        product.setPrice(new BigDecimal(-1));
        try {
            productService.place(Collections.singletonList(product));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
            assertEquals(e.getMessage(),"Product not supported price value [-1]");
        }
    }

    @Test
    public void testPlaceProductThrowExceptionTypeServiceHavePriceNull() {
        TypeService typeService = typeServices.get(2);
        Product product = products.get(0);
        product.setService(typeService);
        product.setPrice(null);
        try {
            productService.place(Collections.singletonList(product));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
            assertEquals(e.getMessage(),"Product not supported price value [null]");
        }
    }


    @Test
    public void testPlaceProductAddTwoEq() {
        TypeService typeService = typeServices.get(2);

        Product product = products.get(0);
        product.setService(typeService);
        Product product1 = products.get(1);
        product1.setService(typeService);

        List<Product> products = productService.place(Arrays.asList(product, product1));

        assertNotNull(products);
        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getPrice().intValue(), product1.getPrice().intValue());
    }
}
