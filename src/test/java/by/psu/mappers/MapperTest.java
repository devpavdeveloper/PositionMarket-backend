package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.postgres.Nsi;
import by.psu.service.api2.ServiceTag;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.StringValueDTO;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.UUID;

public class MapperTest extends BaseTest {

    @Autowired
    private ServiceTag serviceTag;

    private TagNsiMapper nsiMapper = TagNsiMapper.INSTANCE;


    @Test
    public void testMapperNsiToNsiDTO() {
        NsiDTO nsiDTO = nsiMapper.to(serviceTag.getAll().stream().findFirst().get());
        Assert.assertNotNull(nsiDTO);
    }

    @Test
    public void testMapperNsiFromNsiDTO() {
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
