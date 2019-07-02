package by.psu.service.api;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.Nsi;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.Translate;

import java.util.Optional;

import static java.util.Objects.isNull;

public abstract class TranslateUtil {

    public static Optional<StringValue> getValueByLanguage(Translate translate, Language language) {
        if (isNull(language) || isNull(language.getUuid())) {
            return Optional.empty();
        }

        if (isNull(translate) || isNull(translate.getValues()) || translate.getValues().isEmpty()) {
            return Optional.empty();
        }

        return translate.getValues().stream()
                .filter(stringValue -> language.getUuid().equals(stringValue.getLanguage()))
                .findFirst();
    }

    public static <T extends Nsi> Optional<StringValue> getValueOrEmpty(T value, Language language) {
        return getValueByLanguage(value.getTitle(), language);
    }

}
