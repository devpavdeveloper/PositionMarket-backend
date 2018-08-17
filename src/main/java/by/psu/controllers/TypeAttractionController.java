package by.psu.controllers;

import by.psu.model.TypeAttraction;
import by.psu.repository.TypeAttractionRepository;
import by.psu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@CrossOrigin
@RestController
@RequestMapping("/api/types/attractions")
public class TypeAttractionController {

    private final TypeService typeService;

    @Autowired
    public TypeAttractionController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(typeService.findAll());
    }

    @PostMapping("/all")
    public ResponseEntity createAll(@RequestBody TypeAttraction[] typeAttractions){
        return ResponseEntity.ok(typeService.saveOrFind(new ArrayList<>(Arrays.asList(typeAttractions))));
    }
}
