package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.postgres.*;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.ProductDTO;
import by.psu.service.dto.StringValueDTO;
import by.psu.service.dto.TypeServiceDTO;
import by.psu.service.dto.mappers.ProductMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProductMapperTest extends BaseTest {

    @Autowired
    private ProductMapper productMapper;

    private Translate translate;

    @Before
    public void init() {
        translate = new Translate();

        StringValue stringValueRu = new StringValue(Language.RU.getUuid(), "RU", translate);
        StringValue stringValueEn = new StringValue(Language.EN.getUuid(), "EN", translate);

        translate.setValue(stringValueEn);
        translate.setValue(stringValueRu);
    }

    @Test
    public void testMapperProductToProductDTO() {
        Product product = new Product();

        TypeService typeService = new TypeService();
        typeService.setId(UUID.randomUUID());
        typeService.setTitle(translate);


        product.setPrice(new BigDecimal(1234));
        product.setId(UUID.randomUUID());
        product.setService(typeService);
        ProductDTO productDTO = productMapper.to(product);

        assertNotNull(productDTO);
        assertEquals(productDTO.getPrice(), product.getPrice());
        assertEquals(productDTO.getService().getValues().size(), 2);
    }


    @Test
    public void testMapperProductFromProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(new BigDecimal(1234));

        StringValueDTO stringValueDTO = new StringValueDTO();
        stringValueDTO.setLanguage(Language.EN.getUuid());
        stringValueDTO.setValue("EN");

        StringValueDTO stringValueDTO2 = new StringValueDTO();
        stringValueDTO2.setLanguage(Language.RU.getUuid());
        stringValueDTO2.setValue("RU");

        TypeServiceDTO nsiDTO = new TypeServiceDTO();
        nsiDTO.setId(UUID.randomUUID());
        nsiDTO.setValues(Arrays.asList(stringValueDTO, stringValueDTO2));

        productDTO.setService(nsiDTO);

        Product product = productMapper.from( productDTO );

        assertNotNull(product);
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertNotNull(product.getService());
        assertEquals(product.getService().getTitle().getValues().size(), 2);
    }

}
