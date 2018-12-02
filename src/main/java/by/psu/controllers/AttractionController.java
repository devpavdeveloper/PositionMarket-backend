package by.psu.controllers;

import by.psu.model.postgres.Attraction;
import by.psu.service.api2.ServiceAttraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/attractions")
public class AttractionController {

    /*private final ServiceAttraction attractionService;

    @Autowired
    public AttractionController(ServiceAttraction attractionService) {
        this.attractionService = attractionService;
    }

    @GetMapping()
    public ResponseEntity getAttractions(){
        return ResponseEntity.ok(attractionService.getAllAttraction());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAttraction(@PathVariable("id") UUID id){
        return ResponseEntity.ok(attractionService.getAttractionByUUID(id));
    }

   *//* @GetMapping("/search")
    public ResponseEntity getAttractionByTypes(@RequestParam(value = "type", required = false) Long[] indexsAttractions,
                                               @RequestParam(value = "tag", required = false) Long[] indexsTags,
                                               @RequestParam(defaultValue = "0",value = "priceField", required = false) Integer priceField,
                                               @RequestParam(defaultValue = "0",value = "direction", required = false) Integer direction){
        return ResponseEntity.ok(attractionService.findAllFilter(indexsAttractions, indexsTags, priceField, direction));
    }*//*

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Attraction> createAttraction(@RequestBody Attraction attraction){
        return ResponseEntity.ok(attractionService.saveOrUpdate(attraction));
    }

*//*
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateAttraction(@RequestBody Attraction attraction, @PathVariable("id") Long id){
        return ResponseEntity.ok(attractionService.update(attraction, id));
    }
*//*

    @PostMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity createAllAttraction(@RequestBody Attraction[] attractions){
        Arrays.stream(attractions).forEach(attractionService::saveOrUpdate);
        return ResponseEntity.ok().build();
    }

    *//*@DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteAttraction(@PathVariable("id") UUID id){
        attractionService.remove(id);
        return ResponseEntity.ok().build();
    }*/
}
