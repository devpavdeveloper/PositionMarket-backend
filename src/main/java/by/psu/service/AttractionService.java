package by.psu.service;

import by.psu.model.Attraction;

import java.util.ArrayList;
import java.util.List;

public interface AttractionService extends BasicService<Attraction, Long>{
    ArrayList findAllFilter(String[] typeAttractions, Integer priceField, Integer direction);
    List<Attraction> createAll(Attraction[] attractions);
    Attraction findByTitleAttraction(String title);
}
