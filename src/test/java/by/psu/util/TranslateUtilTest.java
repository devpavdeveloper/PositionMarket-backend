package by.psu.util;


import by.psu.BaseTest;
import by.psu.model.factory.FactoryTranslate;
import by.psu.model.postgres.Language;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.Translate;
import by.psu.service.api.TranslateUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

public class TranslateUtilTest extends BaseTest {

    @Autowired
    private FactoryTranslate factoryTranslate;

    private Translate translate;
    private Translate translateWithoutEn;

    @Before
    public void setUp() {
        translate = factoryTranslate.create("Тест", "Test");
        translateWithoutEn = factoryTranslate.create("Тест", Language.RU);
    }

    @Test
    public void testExistsEnglish() {
        Optional<StringValue> stringValue = TranslateUtil.getValueByLanguage(translate, Language.EN);
        assertTrue(stringValue.isPresent());
        assertEquals(stringValue.get().getValue(), "Test");
    }

    @Test
    public void testExistsRussian() {
        Optional<StringValue> stringValue = TranslateUtil.getValueByLanguage(translate, Language.RU);
        assertTrue(stringValue.isPresent());
        assertEquals(stringValue.get().getValue(), "Тест");
    }

    @Test
    public void testWithoutEn() {
        Optional<StringValue> stringValue = TranslateUtil.getValueByLanguage(translateWithoutEn, Language.RU);
        assertTrue(stringValue.isPresent());
        stringValue = TranslateUtil.getValueByLanguage(translateWithoutEn, Language.EN);
        assertFalse(stringValue.isPresent());
    }

    @Test
    public void testWithoutRu() {
        Optional<StringValue> stringValue = TranslateUtil.getValueByLanguage(translateWithoutEn, Language.EN);
        assertFalse(stringValue.isPresent());
    }

}
