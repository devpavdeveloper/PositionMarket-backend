package by.psu.service;

import by.psu.model.Attraction;

import java.util.ArrayList;
import java.util.List;

public interface AttractionService extends BasicService<Attraction, Long>{
    ArrayList findAllFilter(Long[] typeAttractions, Integer priceField, Integer direction);
    List<Attraction> saveOrFind(Attraction[] attractions);
    Attraction findByTitleAttraction(String title);
    Attraction saveOrFind(Attraction attraction);
}
