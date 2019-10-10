package by.psu.mappers.nsi;

import by.psu.BaseTest;
import by.psu.model.factory.FactoryNsi;
import by.psu.model.postgres.Language;
import by.psu.model.postgres.Nsi;
import by.psu.service.api.TranslateUtil;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.StringValueDTO;
import org.junit.Test;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public abstract class MapperNsiTest<T extends Nsi, DTO extends NsiDTO> extends BaseTest {

    protected FactoryNsi<T> factoryNsi;
    protected NsiMapper<T, DTO> mapper;

    @PostConstruct
    protected abstract void init();

    @Test
    public void testMappingNsiToDTO() {
        T object = factoryNsi.create(UUID.randomUUID(), "тест", "test");

        DTO dto = mapper.map(object);

        assertNotNull(dto);

        List<StringValueDTO> stringValueDTOS = dto.getValues();

        assertNotNull(stringValueDTOS);
        assertFalse(stringValueDTOS.isEmpty());
        assertEquals(stringValueDTOS.size(), 2);

        assertTrue(stringValueDTOS.stream().anyMatch(stringValue -> stringValue.getLanguage().equals(Language.EN.getUuid())));
        assertTrue(stringValueDTOS.stream().anyMatch(stringValue -> stringValue.getLanguage().equals(Language.RU.getUuid())));
    }

    @Test
    public void testMappingDTOToNsi() {
        DTO nsiDTO = null;

        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        Class<DTO> type = (Class<DTO>) superClass.getActualTypeArguments()[1];
        try {
            nsiDTO = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        assertNotNull(nsiDTO);

        nsiDTO.setId(UUID.randomUUID());

        List<StringValueDTO> stringValueDTOS = new ArrayList<>();

        StringValueDTO stringValueDTO = new StringValueDTO();
        stringValueDTO.setValue("тест");
        stringValueDTO.setLanguage(Language.RU.getUuid());

        StringValueDTO stringValueDTO1 = new StringValueDTO();
        stringValueDTO1.setValue("test");
        stringValueDTO1.setLanguage(Language.EN.getUuid());

        stringValueDTOS.add(stringValueDTO);
        stringValueDTOS.add(stringValueDTO1);

        nsiDTO.setValues(stringValueDTOS);

        T nsi = mapper.map(nsiDTO);

        assertNotNull(nsi);
        assertEquals(nsi.getId(), nsiDTO.getId());
        assertNotNull(nsi.getTitle());
        assertNotNull(nsi.getTitle().getValues());
        assertFalse(nsi.getTitle().getValues().isEmpty());
        assertEquals(nsi.getTitle().getValues().size(), 2);

        assertTrue(TranslateUtil.getValueOrEmpty(nsi, Language.EN).isPresent());
        assertTrue(TranslateUtil.getValueOrEmpty(nsi, Language.RU).isPresent());

        assertEquals("тест", TranslateUtil.getValueOrEmpty(nsi, Language.RU).get().getValue());
        assertEquals("test", TranslateUtil.getValueOrEmpty(nsi, Language.EN).get().getValue());
    }

}
