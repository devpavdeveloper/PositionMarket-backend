package by.psu.service.api;

import by.psu.model.postgres.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagService extends NsiService<Tag> {

    @Override
    @Transactional
    public void deleteAll(List<UUID> uuid) {
        List<Tag> tags = uuid.stream()
                .map(repositoryNsi::findById)
                .map(Optional::get)
                .peek(nsi -> {
                    nsi.setAttractions(null);
                    entityManager.merge(nsi);
                    entityManager.flush();
                    entityManager.remove(nsi);
                })
                .collect(Collectors.toList());
    }
}
