package by.psu.service.api;

import by.psu.merger.AbstractNsiMerger;
import by.psu.model.postgres.Tag;
import by.psu.model.postgres.repository.RepositoryTag;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
public class TagService extends NsiService<Tag> {

    public TagService(RepositoryTag repositoryTag, AbstractNsiMerger<Tag> tagAbstractNsiMerger) {
        super(repositoryTag, tagAbstractNsiMerger, Tag.class);
    }

    @Override
    protected void deleteConsumer(Tag object) {
        if ( nonNull(object) ) {
            object.getPositions().clear();
        }
    }

}
