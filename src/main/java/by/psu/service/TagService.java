package by.psu.service;

import by.psu.model.Tag;

import java.util.List;

public interface TagService extends BasicService<Tag, Long> {
    List<Tag> saveAll(Tag[] tags);
}
