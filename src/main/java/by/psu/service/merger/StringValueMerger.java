package by.psu.service.merger;

import by.psu.model.postgres.StringValue;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StringValueMerger implements BaseMerger<StringValue> {

    @Override
    public List<StringValue> merge(List<StringValue> first, List<StringValue> second) {
        if (first == null && second == null) {
            return Collections.emptyList();
        }
        if (first == null || first.isEmpty()) {
            return second != null ? second : Collections.emptyList();
        }

        if (second == null || second.isEmpty()) {
            return Collections.emptyList();
        }

        Map<UUID, StringValue> firstMap = first.stream()
                .collect( Collectors.toMap(StringValue::getLanguage, obj -> obj, (value, duplicate) -> value) );


        List<StringValue> result = new ArrayList<>();

        Optional<StringValue> findFirst;

        for (StringValue item : second) {

            if ( item.getId() != null ) {
                findFirst = Optional.ofNullable(firstMap.get(item.getId()));

                if (findFirst.isPresent()) {
                    firstMap.remove(item.getId());
                    result.add( merge(findFirst.get(), item) );
                    continue;
                }
            }

            result.add(item);
        }

        return result;
    }

    @Override
    public StringValue merge(StringValue first, StringValue second) {
        first.setValue( second.getValue() );
        return first;
    }

}
