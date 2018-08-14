package by.psu.service;

import by.psu.model.Tag;
import by.psu.model.TypeAttraction;
import by.psu.repository.TypeAttractionRepository;

import java.util.List;

public interface TypeService extends BasicService<TypeAttraction, Long> {
    List<TypeAttraction> saveAll(TypeAttraction[] typeAttractions);
}
