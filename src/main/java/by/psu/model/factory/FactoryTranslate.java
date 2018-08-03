package by.psu.model.factory;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class FactoryTranslate {

    private final FactoryStringValue factoryStringValue;

    @Autowired
    public FactoryTranslate(FactoryStringValue factoryStringValue) {
        this.factoryStringValue = factoryStringValue;
    }

    private Translate create() {
        return new Translate();
    }

    private Translate create(StringValue ... stringValues) {
        Translate translate = create();
        translate.setListValues(Arrays.asList(stringValues));
        return translate;
    }

    public Translate create(String valueRu, String valueEn) {
        return create( factoryStringValue.create(valueRu, Language.RU), factoryStringValue.create(valueEn, Language.EN) );
    }

    public Translate create(String value, Language language) {
        return create( factoryStringValue.create(value, language) );
    }

}
