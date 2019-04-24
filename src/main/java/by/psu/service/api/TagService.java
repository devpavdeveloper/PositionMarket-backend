package by.psu.service.api;

import by.psu.model.postgres.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TagService extends NsiService<Tag> {

    @Transactional
    public void deleteAll(List<UUID> uuid) {
        super.deleteAll(uuid, nsi -> {
            nsi.setAttractions(null);
        });
    }
}
