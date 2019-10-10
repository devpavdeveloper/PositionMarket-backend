package by.psu.model.factory;

import by.psu.model.postgres.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryAttraction {

    @Autowired
    private FactoryTranslate factoryTranslate;

    public Position create() {
        return new Position();
    }

    public Position create(String titleRu, String titleEn) {
        Position position = create();
        position.setTitle( factoryTranslate.create(titleRu, titleEn) );
        return position;
    }

}
