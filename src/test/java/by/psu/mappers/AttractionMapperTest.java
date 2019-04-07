package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.factory.*;
import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Language;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.AttractionMapper;
import by.psu.service.dto.mappers.ProductMapper;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import by.psu.service.dto.mappers.nsi.TypeNsiMapper;
import by.psu.service.dto.mappers.nsi.TypeServiceNsiMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AttractionMapperTest extends BaseTest {

    @Autowired
    private AttractionMapper mapper;

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


    @Autowired
    private TagNsiMapper tagNsiMapper;

    @Autowired
    private TypeNsiMapper typeNsiMapper;

    @Autowired
    private TypeServiceNsiMapper typeServiceNsiMapper;

    @Autowired
    private ProductMapper productMapper;

    private Attraction attraction;

    @Before
    public void init() {
        attraction = factoryAttraction.create("Новый", "New");
    }

    @Test
    public void testMapperAttractionToAttractionDTOCommonInformation() {
        AttractionDTO attractionDTO = mapper.to(attraction);

        assertNotNull(attractionDTO);
        assertTrue(attractionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.RU.getUuid()) && item.getValue().equals("Новый")));
        assertTrue(attractionDTO.getTitle().stream()
                .anyMatch(item -> item.getLanguage().equals(Language.EN.getUuid()) && item.getValue().equals("New")));
    }


/*
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

        assertTrue(products.stream().anyMatch(product -> TranslateUtil.getValueOrEmpty(product.getService(), Language.EN).isPresent()));
        assertTrue(products.stream().anyMatch(product -> TranslateUtil.getValueOrEmpty(product.getService(), Language.RU).isPresent()));

        List<StringValue> stringValuesRu = products.stream()
                .map(product -> TranslateUtil.getValueOrEmpty(product.getService(), Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Доставка")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Установка")));

        List<StringValue> stringValuesEn = products.stream()
                .map(product -> TranslateUtil.getValueOrEmpty(product.getService(), Language.EN).get())
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

        assertTrue(tags.stream().anyMatch(tag -> TranslateUtil.getValueOrEmpty(tag, Language.EN).isPresent()));
        assertTrue(tags.stream().anyMatch(tag -> TranslateUtil.getValueOrEmpty(tag, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = tags.stream()
                .map(tag -> TranslateUtil.getValueOrEmpty(tag, Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные 2018")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные 2019")));

        List<StringValue> stringValuesEn = tags.stream()
                .map(tag -> TranslateUtil.getValueOrEmpty(tag, Language.EN).get())
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

        assertTrue(types.stream().anyMatch(type -> TranslateUtil.getValueOrEmpty(type, Language.EN).isPresent()));
        assertTrue(types.stream().anyMatch(type -> TranslateUtil.getValueOrEmpty(type, Language.RU).isPresent()));

        List<StringValue> stringValuesRu = types.stream()
                .map(type -> TranslateUtil.getValueOrEmpty(type, Language.RU).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Спортивные")));
        assertTrue(stringValuesRu.stream().anyMatch(stringValue -> stringValue.getValue().equals("Интерактивные")));

        List<StringValue> stringValuesEn = types.stream()
                .map(type -> TranslateUtil.getValueOrEmpty(type, Language.EN).get())
                .collect(Collectors.toList());

        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Sport")));
        assertTrue(stringValuesEn.stream().anyMatch(stringValue -> stringValue.getValue().equals("Interactive")));
    }
*/

}
