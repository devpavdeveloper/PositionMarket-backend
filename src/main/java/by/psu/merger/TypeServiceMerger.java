package by.psu.merger;

import by.psu.model.postgres.Translate;
import by.psu.model.postgres.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TypeServiceMerger extends AbstractNsiMerger<TypeService> {

    @Autowired
    public TypeServiceMerger(TranslateObjectMerger translateObjectMerger) {
        super(translateObjectMerger);
    }

    @Override
    public TypeService merge(TypeService first, TypeService second) {
        super.merge(first, second);
        Translate descriptionTranslate =
                translateObjectMerger.merge(first.getDescription(), second.getDescription());

        first.setDescription(descriptionTranslate);
        first.setType(second.getType());
        return first;
    }

}
