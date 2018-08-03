package by.psu.controllers;

import by.psu.repository.TypeAttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/types/attractions")
public class TypeAttractionController {

    private final TypeAttractionRepository repository;

    @Autowired
    public TypeAttractionController(TypeAttractionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(repository.findAll());
    }
}
