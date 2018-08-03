package by.psu.model.factory;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.StringValue;
import org.springframework.stereotype.Component;

@Component
public class FactoryStringValue {

    private StringValue create() {
        return new StringValue();
    }

    public StringValue create(String value, Language language) {
        StringValue stringValue = create();
        stringValue.setLanguage(language.getUuid());
        stringValue.setValue(value);
        return stringValue;
    }
}
