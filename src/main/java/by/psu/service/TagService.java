package by.psu.service;

import by.psu.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface TagService extends BasicService<Tag, Long> {
    Set<Tag> saveOrFind(Collection<Tag> tags);
}
