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

    public Attraction create(String titleRu, String titleEn) {
        Attraction attraction = create();
        attraction.setTitle( factoryTranslate.create(titleRu, titleEn) );
        return attraction;
    }

}
