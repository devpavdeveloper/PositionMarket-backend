package by.psu.service.api2;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.Translate;

import java.util.Optional;

public abstract class LanguageUtil {

    public static Optional<StringValue> getValueByLanguage(Translate translate, Language language) {
        if ( translate != null) {
            if ( translate.getValues() != null && !translate.getValues().isEmpty() ) {
                return translate.getValues().stream()
                        .filter(stringValue -> language.getUuid().equals(stringValue.getLanguage()))
                        .findFirst();
            }
        }
        return Optional.empty();
    }

}
