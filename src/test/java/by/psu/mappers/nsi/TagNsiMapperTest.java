package by.psu.mappers.nsi;

import by.psu.model.factory.FactoryTag;
import by.psu.model.postgres.Tag;
import by.psu.service.dto.TagDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class TagNsiMapperTest extends MapperNsiTest<Tag, TagDTO> {

    @Autowired
    private FactoryTag factoryTag;
    @Autowired
    private TagNsiMapper tagNsiMapper;

    @Override
    protected void init() {
        super.factoryNsi = factoryTag;
        super.mapper = tagNsiMapper;
    }
}
