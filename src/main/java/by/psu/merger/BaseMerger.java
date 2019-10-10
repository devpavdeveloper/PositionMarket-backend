package by.psu.merger;

import by.psu.model.postgres.BasicEntity;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public interface BaseMerger<T extends BasicEntity> {

    default List<T> merge(List<T> first, List<T> second) {
        if (!requireValidLists(first, second)) {
            return Collections.emptyList();
        }

        Map<UUID, T> firstMap = first.stream()
                .collect( Collectors.toMap(BasicEntity::getId, obj -> obj, (value, duplicate) -> value));


        List<T> result = new ArrayList<>();

        T findFirst;

        for (T item : second) {

            if ( nonNull(item.getId()) ) {
                findFirst = firstMap.get(item.getId());

                if (nonNull(findFirst)) {
                    firstMap.remove(item.getId());
                    result.add(merge(findFirst, item));
                    continue;
                }
            }

            result.add(item);
        }

        return result;
    }

    default public boolean requireValidLists(List<T> first, List<T> second) {
        boolean firstOrSecondIsNull = first == null && second == null;
        if (firstOrSecondIsNull) {
            return false;
        }

        boolean firstIsNullOrEmpty = first == null || first.isEmpty();
        if (firstIsNullOrEmpty) {
            return false;
        }

        boolean secondIsNullOrEmpty = second == null || second.isEmpty();
        if (secondIsNullOrEmpty) {
            return false;
        }

        return true;
    }

    T merge(T first, T second);
}
