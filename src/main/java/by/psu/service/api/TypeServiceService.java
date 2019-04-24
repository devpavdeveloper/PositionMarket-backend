package by.psu.service.api;

import by.psu.model.postgres.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TypeServiceService extends NsiService<TypeService> {

    @Transactional
    public void deleteAll(List<UUID> uuid) {
        super.deleteAll(uuid, nsi -> {
            nsi.setProducts(null);
        });
    }

}
