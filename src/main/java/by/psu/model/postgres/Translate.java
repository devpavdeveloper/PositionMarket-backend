package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;


@Entity(
        name = "translate"
)
@Table(
        name = "translate"
)
@Getter @Setter
public class Translate extends BasicEntity {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "translate")
    List<StringValue> stringValues;

    public Optional<StringValue> setValue(StringValue stringValue) {
        Optional<StringValue> optionalValue = Optional.ofNullable(stringValue);

        if ( optionalValue.isPresent() ) {
            Optional.of(stringValue.getLanguage()).orElseThrow(() -> new RuntimeException("Language is null"));

            Arrays.stream(Language.values())
                    .filter(language -> language.getUuid().equals(optionalValue.get().getLanguage()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Language with id" + optionalValue.get().getLanguage() + " not supported"));

            Optional.of(stringValue.getValue()).orElseThrow(() -> new RuntimeException("Value is null or empty"));
            stringValues = Optional.ofNullable(stringValues).orElseGet(ArrayList::new);

            Optional<StringValue> stringValueFounded = stringValues.stream()
                    .filter(value -> value.getLanguage().equals(optionalValue.get().getLanguage()))
                    .findFirst();

            if ( stringValues.isEmpty() || !stringValueFounded.isPresent() ) {
                stringValues.add(optionalValue.get());
                return optionalValue;
            } else {
                stringValueFounded.get().setValue(optionalValue.get().getValue());
                return stringValueFounded;
            }
        }

        return Optional.empty();
    }

    public Optional<List<StringValue>> setValue(List<StringValue> stringValues) {
        Optional<List<StringValue>> optionalStringValueList = Optional.ofNullable(stringValues);
        return optionalStringValueList
                .map(stringValues1 -> stringValues1.stream()
                        .map(value -> setValue(value).orElse(null))
                        .filter(Objects::isNull)
                        .collect(Collectors.toList())
                );
    }
}
