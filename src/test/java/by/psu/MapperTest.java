package by.psu;

import by.psu.model.postgres.Nsi;
import by.psu.service.api2.ServiceTag;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.StringValueDTO;
import by.psu.service.dto.mappers.TagNsiMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.UUID;

public class MapperTest extends BaseTest {

    @Autowired
    private ServiceTag serviceTag;

    @Test
    public void testMapperNsiToNsiDTO() {
        TagNsiMapper nsiMapper = TagNsiMapper.INSTANCE;
        NsiDTO nsiDTO = nsiMapper.to(serviceTag.getAll().stream().findFirst().get());
        Assert.assertNotNull(nsiDTO);
    }

    @Test
    public void testMapperNsiFromNsiDTO() {
        TagNsiMapper nsiMapper = TagNsiMapper.INSTANCE;
        NsiDTO nsiDTO = new NsiDTO();
        nsiDTO.setId(UUID.randomUUID());

        StringValueDTO stringValueDTO = new StringValueDTO();
        stringValueDTO.setLanguage(UUID.randomUUID());
        stringValueDTO.setValue("ok");

        nsiDTO.setValues(Collections.singletonList(stringValueDTO));
        Nsi nsi = nsiMapper.from(nsiDTO);
        Assert.assertNotNull(nsi);
    }
}
