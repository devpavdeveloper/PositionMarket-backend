package by.psu.controllers;

import by.psu.service.dto.AttractionDTO;
import by.psu.service.facade.AttractionFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/attractions")
public class PositionController {

    private final AttractionFacade attractionFacade;

    @Autowired
    public PositionController(AttractionFacade attractionFacade) {
        this.attractionFacade = attractionFacade;
    }


    @GetMapping()
    public ResponseEntity getAll(){
        return ResponseEntity.ok(attractionFacade.getAll());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity getOne(@PathVariable UUID uuid){
        return ResponseEntity.ok(attractionFacade.getOne(uuid));
    }

    @PostMapping
    public ResponseEntity<AttractionDTO> create(@RequestBody AttractionDTO attraction){
        return ResponseEntity.ok(attractionFacade.save(attraction));
    }

    @PutMapping
    public ResponseEntity<AttractionDTO> update(@RequestBody AttractionDTO attraction){
        return ResponseEntity.ok(attractionFacade.update(attraction));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        attractionFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

}
