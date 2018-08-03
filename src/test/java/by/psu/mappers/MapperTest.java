package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.service.api.TagService;
import by.psu.service.dto.NsiDTO;
import by.psu.service.dto.mappers.nsi.TagNsiMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperTest extends BaseTest {

    @Autowired
    private TagService serviceTag;

    @Autowired
    private TagNsiMapper nsiMapper;

    @Test
    public void testMapperNsiToNsiDTO() {
        NsiDTO nsiDTO = nsiMapper.to(serviceTag.getAll().stream().findFirst().get());
        Assert.assertNotNull(nsiDTO);
    }

    @Test
    public void testMapperNsiFromNsiDTO() {}
}
