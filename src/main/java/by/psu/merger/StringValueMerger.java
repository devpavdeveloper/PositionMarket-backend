package by.psu.merger;

import by.psu.model.postgres.StringValue;
import org.springframework.stereotype.Component;

@Component
public class StringValueMerger implements BaseMerger<StringValue> {

    @Override
    public StringValue merge(StringValue first, StringValue second) {
        first.setValue( second.getValue() );
        return first;
    }

}
