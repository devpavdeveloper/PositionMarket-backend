package by.psu.service.api2;

import by.psu.model.postgres.TypeService;
import org.springframework.stereotype.Service;

@Service
public class ServiceTypeService extends ServiceNsi<TypeService> {

    public ServiceTypeService() {
        super(TypeService.class);
    }
}
