package by.psu.controllers;

import by.psu.model.Attraction;
import by.psu.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @Autowired
    public AttractionController(AttractionService attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping()
    public ResponseEntity getAttractions(){
        return ResponseEntity.ok(attractionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAttraction(@PathVariable("id") Long id){
        return ResponseEntity.ok(attractionService.findById(id));
    }

    @GetMapping("/search")
    public ResponseEntity getAttractionByTypes(@RequestParam(value = "type", required = false) String[] typeAttractions,
                                               @RequestParam(defaultValue = "0",value = "priceField", required = false) Integer priceField,
                                               @RequestParam(defaultValue = "0",value = "direction", required = false) Integer direction){
        return ResponseEntity.ok(attractionService.findAllFilter(typeAttractions, priceField, direction));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Attraction> createAttraction(@RequestBody Attraction attraction){
        return ResponseEntity.ok(attractionService.update(attraction, attraction.getId()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateAttraction(@RequestBody Attraction attraction, @PathVariable("id") Long id){
        return ResponseEntity.ok(attractionService.update(attraction, id));
    }

    @PostMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createAllAttraction(@RequestBody Attraction[] attraction){
        return ResponseEntity.ok(attractionService.createAll(attraction));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteAttraction(@PathVariable("id") Long id){
        attractionService.remove(id);
        return ResponseEntity.ok().build();
    }
}
