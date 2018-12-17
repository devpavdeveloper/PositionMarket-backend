package by.psu.service.merger;

import by.psu.model.postgres.Translate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TranslateObjectMerger implements BaseMerger<Translate> {

    @Autowired
    private StringValueMerger stringValueMerger;

    @Override
    public Translate merge(Translate first, Translate second) {
        if ( isNotValidTranslate(first) ) {
            return second;
        }

        if ( isNotValidTranslate(second) ) {
            return first;
        }

        first.setListValues( stringValueMerger.merge( first.getValues(), second.getValues() ) );
        return first;
    }

    private boolean isNotValidTranslate (Translate obj) {
        return obj == null || obj.getValues() == null || obj.getValues().isEmpty();
    }
}
