package by.psu.merger;

import by.psu.model.postgres.Nsi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbstractNsiMerger<T extends Nsi> implements NsiMerger<T> {

    protected final TranslateObjectMerger translateObjectMerger;

    @Autowired
    public AbstractNsiMerger(TranslateObjectMerger translateObjectMerger) {
        this.translateObjectMerger = translateObjectMerger;
    }

    @Override
    public T merge(T first, T second) {

        if ( isNotValidNsi(first) ) {
            return second;
        }

        if ( isNotValidNsi(second) ) {
            return first;
        }

        translateObjectMerger.merge(first.getTitle(), second.getTitle());

        return first;
    }

    private boolean isNotValidNsi(T obj) {
        return obj == null || obj.getTitle() == null || obj.getTitle().getValues().isEmpty();
    }
}
