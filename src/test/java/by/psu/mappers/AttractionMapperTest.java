package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.factory.*;
import by.psu.model.postgres.*;
import by.psu.service.api.LanguageUtil;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.AttractionMapper;
import by.psu.service.dto.mappers.ProductMapper;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import by.psu.service.dto.mappers.nsi.TypeNsiMapper;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class AttractionMapperTest extends BaseTest {

    private AttractionMapper mapper = AttractionMapper.INSTANCE;

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


    private TagNsiMapper tagNsiMapper = TagNsiMapper.INSTANCE;
    private TypeNsiMapper typeNsiMapper = TypeNsiMapper.INSTANCE;
    private TypeServiceNsiMapper typeServiceNsiMapper = TypeServiceNsiMapper.INSTANCE;
    private ProductMapper productMapper = ProductMapper.INSTANCE;

    private Attraction attraction;


    @Before
    public void init() {
        attraction = factoryAttraction.create("Новый", "New", "http://link", "http://image");
    }


    @Test
    public void testMapperAttractionToAttractionDTOCommonInformation() {
        AttractionDTO attractionDTO = mapper.to(attraction);

        assertNotNull(attractionDTO);
        assertEquals(attractionDTO.getImage(), "http://image");
        assertEquals(attractionDTO.getLink(), "http://link");
        assertTrue(attractionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.RU.getUuid()) && item.getValue().equals("Новый")));
        assertTrue(attractionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.EN.getUuid()) && item.getValue().equals("New")));
    }


    @Test
    public void testMapperAttractionToAttractionDTOAllInformationExistsProducts() {
        attraction.setProducts(Arrays.asList(
                factoryProduct.create(10, factoryTypeService.create("Доставка", "Pick Service")),
                factoryProduct.create(12, factoryTypeService.create("Установка", "Install Service"))
        ));

        AttractionDTO attractionDTO = mapper.to(attraction);

        assertNotNull(attractionDTO);

        assertTrue(attractionDTO.getProducts().stream().anyMatch(product -> product.getPrice().equals(new BigDecimal(10))));
        assertTrue(attractionDTO.getProducts().stream().anyMatch(product -> product.getPrice().equals( new BigDecimal(12))));

        List<Product> products = attractionDTO.getProducts().stream().map(product -> productMapper.from(product)).collect(toList());

        assertTrue(products.stream().anyMatch(product -> LanguageUtil.getValueOrEmpty(product.getService(), Language.EN).isPresent()));
        assertTrue(products.stream().anyMatch(product -> LanguageUtil.getValueOrEmpty(product.getService(), Language.RU).isPresent()));

        List<StringValue> stringValuesRu = products.stream()
                .map(product -> LanguageUtil.getValueOrEmpty(product.getService(), Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Доставка")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Установка")));

        List<StringValue> stringValuesEn = products.stream()
                .map(product -> LanguageUtil.getValueOrEmpty(product.getService(), Language.EN).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Pick Service")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Install Service")));
    }


    @Test
    public void testMapperAttractionToAttractionDTOAllInformationExistsTags() {
        attraction.setTags(Arrays.asList(
                factoryTag.create("Спортивные 2018", "Sport 2018"),
                factoryTag.create("Интерактивные 2019", "Interactive 2019")
        ));

        AttractionDTO attractionDTO = mapper.to(attraction);

        assertNotNull(attractionDTO);

        List<Tag> tags = attractionDTO.getTags().stream().map(tag -> tagNsiMapper.from(tag)).collect(toList());

        assertTrue(tags.stream().anyMatch(tag -> LanguageUtil.getValueOrEmpty(tag, Language.EN).isPresent()));
        assertTrue(tags.stream().anyMatch(tag -> LanguageUtil.getValueOrEmpty(tag, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = tags.stream()
                .map(tag -> LanguageUtil.getValueOrEmpty(tag, Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные 2018")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные 2019")));

        List<StringValue> stringValuesEn = tags.stream()
                .map(tag -> LanguageUtil.getValueOrEmpty(tag, Language.EN).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Sport 2018")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Interactive 2019")));
    }


    @Test
    public void testMapperAttractionToAttractionDTOAllInformationExistsTypes() {
        attraction.setTypes(Arrays.asList(
                factoryType.create("Спортивные", "Sport"),
                factoryType.create("Интерактивные", "Interactive")
        ));

        AttractionDTO attractionDTO = mapper.to(attraction);

        assertNotNull(attractionDTO);

        List<Type> types = attractionDTO.getTypes().stream().map(type -> typeNsiMapper.from(type)).collect(toList());

        assertTrue(types.stream().anyMatch(type -> LanguageUtil.getValueOrEmpty(type, Language.EN).isPresent()));
        assertTrue(types.stream().anyMatch(type -> LanguageUtil.getValueOrEmpty(type, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = types.stream()
                .map(type -> LanguageUtil.getValueOrEmpty(type, Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные")));

        List<StringValue> stringValuesEn = types.stream()
                .map(type -> LanguageUtil.getValueOrEmpty(type, Language.EN).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Sport")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Interactive")));
    }

}
