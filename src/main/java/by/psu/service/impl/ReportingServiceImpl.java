package by.psu.service.impl;

import by.psu.model.Attraction;
import by.psu.repository.AttractionRepository;
import by.psu.service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportingServiceImpl implements ReportingService{

    private final AttractionRepository attractionRepository;

    @Autowired
    public ReportingServiceImpl(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    @Override
    public List<Attraction> getAllAttractionsInfo() {
        return attractionRepository.findAll();
    }
}
