package by.psu.service;

import by.psu.model.TypeAttraction;

import java.util.Collection;
import java.util.Set;

public interface TypeService extends BasicService<TypeAttraction, Long> {
    Set<TypeAttraction> saveOrFind(Collection<TypeAttraction> typeAttractions);
}
