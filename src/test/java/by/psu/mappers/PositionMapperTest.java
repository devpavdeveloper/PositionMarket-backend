package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.mappers.nsi.TagNsiMapper;
import by.psu.mappers.nsi.TypeNsiMapper;
import by.psu.model.factory.*;
import by.psu.model.postgres.*;
import by.psu.service.api.TypeService;
import by.psu.service.api.*;
import by.psu.service.dto.PositionDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class PositionMapperTest extends BaseTest {

    @Autowired
    private PositionMapper mapper;

    @Autowired
    private FactoryAttraction factoryAttraction;
    @Autowired
    private FactoryType factoryType;
    @Autowired
    private FactoryTypeService factoryTypeService;
    @Autowired
    private FactoryProduct factoryProduct;
    @Autowired
    private FactoryTag factoryTag;
    @MockBean
    private ProductService productService;
    @MockBean
    private TypeServiceService typeServiceService;

    @Autowired
    private TagNsiMapper tagNsiMapper;

    @Autowired
    private TypeNsiMapper typeNsiMapper;

    @Autowired
    private ProductMapper productMapper;

    private Position position;

    @MockBean
    private TypeService typeService;
    @MockBean
    private TagService tagService;


    @Before
    public void init() {
        position = factoryAttraction.create("Новый", "New");
    }

    @Test
    public void testMapperAttractionToAttractionDTOCommonInformation() {
        PositionDTO positionDTO = mapper.map(position);

        assertNotNull(positionDTO);
        assertTrue(positionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.RU.getUuid()) && item.getValue().equals("Новый")));
        assertTrue(positionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.EN.getUuid()) && item.getValue().equals("New")));
    }


    @Test
    public void testMapperAttractionToAttractionDTOWithProductAndSetService() {
        by.psu.model.postgres.TypeService typeService1 = factoryTypeService.create(UUID.randomUUID(), "Доставка", "Pick Service");
        by.psu.model.postgres.TypeService typeService2 = factoryTypeService.create(UUID.randomUUID(), "Установка", "Install Service");

        Product product1 = factoryProduct.create(UUID.randomUUID(), 10, typeService1);
        Product product2 = factoryProduct.create(UUID.randomUUID(), 12, typeService2);

        position.setProducts(Arrays.asList(product1, product2));

        PositionDTO positionDTO = mapper.map(position);

        assertNotNull(positionDTO);

        assertTrue(positionDTO.getProducts().stream().anyMatch(product -> product.getPrice().equals(new BigDecimal(10))));
        assertTrue(positionDTO.getProducts().stream().anyMatch(product -> product.getPrice().equals( new BigDecimal(12))));

        List<Product> products = positionDTO.getProducts().stream()
                .map(productMapper::map)
                .collect(toList());

        assertTrue(products.stream().anyMatch(product -> product.getService().getId().equals(typeService1.getId())));
        assertTrue(products.stream().anyMatch(product -> product.getService().getId().equals(typeService2.getId())));
    }


    @Test
    public void testMapperAttractionToAttractionDTOAllInformationExistsTags() {
        Tag tag1 = factoryTag.create(UUID.randomUUID(),"Спортивные 2018", "Sport 2018");
        Tag tag2 = factoryTag.create(UUID.randomUUID(), "Интерактивные 2019", "Interactive 2019");

        when(tagService.getOne(tag1.getId()))
                .thenReturn(tag1);
        when(tagService.getOne(tag2.getId()))
                .thenReturn(tag2);

        position.setTags(Arrays.asList(tag1, tag2));

        PositionDTO positionDTO = mapper.map(position);

        assertNotNull(positionDTO);

        List<Tag> tags = positionDTO.getTags().stream()
                .map(tagService::getOne)
                .collect(toList());

        assertTrue(tags.stream().anyMatch(tag -> TranslateUtil.getValueOrEmpty(tag, Language.EN).isPresent()));
        assertTrue(tags.stream().anyMatch(tag -> TranslateUtil.getValueOrEmpty(tag, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = tags.stream()
                .map(tag -> TranslateUtil.getValueOrEmpty(tag, Language.RU))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные 2018")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные 2019")));

        List<StringValue> stringValuesEn = tags.stream()
                .map(tag -> TranslateUtil.getValueOrEmpty(tag, Language.EN))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Sport 2018")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Interactive 2019")));
    }


    @Test
    public void testMapperAttractionToAttractionDTOAllInformationExistsTypes() {
        Type type1 = factoryType.create(UUID.randomUUID(), "Спортивные", "Sport");
        Type type2 = factoryType.create(UUID.randomUUID(), "Интерактивные", "Interactive");

        position.setTypes(Arrays.asList(type1, type2));

        when(typeService.getOne(type1.getId()))
                .thenReturn(type1);
        when(typeService.getOne(type2.getId()))
                .thenReturn(type2);

        PositionDTO positionDTO = mapper.map(position);

        assertNotNull(positionDTO);

        List<Type> types = positionDTO.getTypes().stream()
                .map(typeService::getOne)
                .collect(toList());

        assertTrue(types.stream().anyMatch(type -> TranslateUtil.getValueOrEmpty(type, Language.EN).isPresent()));
        assertTrue(types.stream().anyMatch(type -> TranslateUtil.getValueOrEmpty(type, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = types.stream()
                .map(type -> TranslateUtil.getValueOrEmpty(type, Language.RU))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные")));

        List<StringValue> stringValuesEn = types.stream()
                .map(type -> TranslateUtil.getValueOrEmpty(type, Language.EN))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Sport")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Interactive")));
    }

}
