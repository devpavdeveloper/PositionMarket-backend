package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.postgres.Attraction;
import by.psu.service.dto.AttractionDTO;
import by.psu.service.dto.mappers.AttractionMapper;
import org.junit.Test;

public class AttractionMapperTest extends BaseTest {

    private AttractionMapper mapper = AttractionMapper.INSTANCE;


    @Test
    public void testMapperAttractionToAttractionDTO() {
        AttractionDTO attractionDTO = mapper.to(new Attraction());
    }


}
