package by.psu.service.merger;

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
        translateObjectMerger.merge(first.getDescription(), second.getDescription());
        first.setType(second.getType());
        return first;
    }

}
