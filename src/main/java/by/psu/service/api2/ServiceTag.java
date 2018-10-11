package by.psu.service.api2;

import by.psu.model.postgres.Tag;
import by.psu.model.postgres.repository.RepositoryTag;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ServiceTag {

    private final RepositoryTag repositoryTag;


    @Autowired
    public ServiceTag(RepositoryTag repositoryTag) {
        this.repositoryTag = repositoryTag;
    }

    @Transactional
    public Tag saveOrFind(@NotNull Tag tag) {
        Tag tagFind = repositoryTag.findByTitle(tag.getTitle());
        if (isNull(tagFind)) {
            return repositoryTag.save(tag);
        }
        return tagFind;
    }

    @Transactional
    public Tag update(@NotNull String title, UUID uuid) {
        Tag tagFind = repositoryTag.findById(uuid).orElse(new Tag());
        if (isNull(tagFind.getId())) {
            tagFind.setTitle(title);
            return repositoryTag.save(tagFind);
        }
        Tag tagByTitle = repositoryTag.findByTitle(title);

        if (nonNull(tagByTitle)) {
            return tagByTitle;
        }

        tagFind.setTitle(title);
        return repositoryTag.save(tagFind);
    }

    public List<Tag> getAllTags() {
        return repositoryTag.findAll();
    }

    public Tag getOne(UUID uuid) {
        return repositoryTag.findById(uuid).orElse(null);
    }
}
