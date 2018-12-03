package by.psu.service.api2;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.StringValue;
import by.psu.model.postgres.Translate;

import java.util.ArrayList;
import java.util.Optional;

public class LanguageUtil {

    public static Optional<StringValue> getValueByLanguage(Translate translate, Language language) {
        if ( translate != null) {
            if ( translate.getStringValues() != null && !translate.getStringValues().isEmpty() ) {
                return translate.getStringValues().stream()
                        .filter(stringValue -> language.getUuid().equals(stringValue.getLanguage()))
                        .findFirst();
            }
        }
        return Optional.empty();
    }

    public static Optional<Translate> setValueToTranslateMutable(Translate translate, StringValue stringValue) {
        Optional<StringValue> optionalStringValue = setValueToTranslateUnmutable(translate, stringValue);
        if ( stringValue != null) {
            if (stringValue.getValue() == null) {
                stringValue.setValue("");
            }
            optionalStringValue.ifPresent(value -> value.setValue(stringValue.getValue()));
        }
        return Optional.empty();
    }


    public static Optional<StringValue> setValueToTranslateUnmutable(Translate translate, StringValue stringValue) {
        if ( translate != null && stringValue != null ) {

            if (stringValue.getLanguage() == null) {
                throw new RuntimeException("StringValue have a language is null");
            }

            if (translate.getStringValues() == null) {
                translate.setStringValues(new ArrayList<>());
            }

            Optional<StringValue> optionalStringValue = translate.getStringValues().stream()
                    .filter(value -> value.getLanguage().equals(stringValue.getLanguage()))
                    .findFirst();


            if ( !optionalStringValue.isPresent() ) {
                if ( stringValue.getValue() == null) {
                    stringValue.setValue("");
                }
                stringValue.setTranslate(translate);
                translate.getStringValues().add(stringValue);
                return Optional.of(stringValue);
            }

            return optionalStringValue;
        }
        return Optional.empty();
    }

}
