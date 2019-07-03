package by.psu.mappers;

import by.psu.BaseTest;
import by.psu.model.postgres.Attraction;
import by.psu.model.postgres.Image;
import by.psu.model.postgres.PositionImage;
import by.psu.service.dto.PositionImageDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class PositionImageMapperTest extends BaseTest {

    @Autowired
    private PositionImageMapper positionImageMapper;

    private PositionImage positionImage;
    private PositionImageDTO positionImageDTO;

    @Before
    public void setUp() throws Exception {
        positionImage = new PositionImage();

        Attraction attraction = new Attraction();
        attraction.setId(UUID.randomUUID());

        positionImage.setMainImage(false);

        Image image = new Image();
        image.setId(UUID.randomUUID());

        positionImage.setImage(image);

        positionImageDTO = new PositionImageDTO();

        positionImageDTO.setImage(UUID.randomUUID());
        positionImageDTO.setMainImage(true);

    }

    @Test
    public void testPositionMapperPositionImageDTOToPositionImage() {
        PositionImage positionImage = positionImageMapper.map(positionImageDTO);

        Assert.assertNotNull(positionImage);
        Assert.assertNotNull(positionImage.getImage());

        Assert.assertEquals(positionImage.getImage().getId(), positionImageDTO.getImage());
        Assert.assertEquals(positionImage.isMainImage(), positionImageDTO.isMainImage());
    }

    @Test
    public void testPositionMapperPositionImageToPositionDTO() {
        PositionImageDTO positionImageDTO = positionImageMapper.map(positionImage);

        Assert.assertNotNull(positionImageDTO);
        Assert.assertNotNull(positionImageDTO.getImage());

        Assert.assertEquals(positionImage.getImage().getId(), positionImageDTO.getImage());
        Assert.assertEquals(positionImage.isMainImage(), positionImageDTO.isMainImage());
    }

}
