package by.psu.service.merger;

import by.psu.model.postgres.BasicEntity;

import java.util.*;
import java.util.stream.Collectors;

public interface BaseMerger<T extends BasicEntity> {

    default public List<T> merge(List<T> first, List<T> second) {
        if (first == null && second == null) {
            return Collections.emptyList();
        }
        if (first == null || first.isEmpty()) {
            return second != null ? second : Collections.emptyList();
        }

        if (second == null || second.isEmpty()) {
            return Collections.emptyList();
        }

        Map<UUID, T> firstMap = first.stream().collect( Collectors.toMap(BasicEntity::getId, obj -> obj) );


        List<T> result = new ArrayList<>();

        Optional<T> findFirst;

        for (T item : second) {

            if ( item.getId() != null ) {
                findFirst = Optional.ofNullable(firstMap.get(item.getId()));

                if (findFirst.isPresent()) {
                    firstMap.remove(item.getId());
                    result.add(merge(findFirst.get(), item));
                    continue;
                }
            }

            result.add(item);
        }

        return result;
    }

    public T merge(T first, T second);
}
