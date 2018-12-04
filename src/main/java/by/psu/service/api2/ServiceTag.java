package by.psu.service.api2;

import by.psu.model.postgres.Tag;
import org.springframework.stereotype.Service;

@Service
public class ServiceTag extends ServiceNsi<Tag> {

    public ServiceTag() {
        super(Tag.class);
    }



}
