package by.psu.service.api;

import by.psu.model.postgres.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TypeService extends NsiService<Type> {

    @Transactional
    public void deleteAll(List<UUID> uuid) {
        super.deleteAll(uuid, nsi -> {
            nsi.setAttractions(null);
        });
    }

}
