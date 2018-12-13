package by.psu.model.factory;

import by.psu.model.postgres.Attraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryAttraction {

    @Autowired
    private FactoryTranslate factoryTranslate;

    public Attraction create() {
        return new Attraction();
    }

    private Attraction create(String titleRu, String titleEn) {
        Attraction attraction = create();
        attraction.setTitle( factoryTranslate.create(titleRu, titleEn) );
        return attraction;
    }

    public Attraction create(String titleRu, String titleEn, String link, String image) {
        Attraction attraction = create(titleRu, titleEn);
        attraction.setImage(image);
        attraction.setLinkSource(link);
        return attraction;
    }

}
