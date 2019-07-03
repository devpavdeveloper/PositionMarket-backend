package by.psu.controllers;

import by.psu.facade.AttractionFacade;
import by.psu.service.dto.AttractionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public ResponseEntity getAll(@RequestParam(value = "page", defaultValue = "0") int pageIndex,
                                 @RequestParam(value = "size", defaultValue = "99999") int pageSize){
        return ResponseEntity.ok(attractionFacade.getAll(PageRequest.of(pageIndex, pageSize)));
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

    @DeleteMapping("/[{uuids}]")
    public ResponseEntity multipleDelete(@PathVariable UUID[] uuids){
        attractionFacade.delete(Arrays.asList(uuids));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity delete(@PathVariable UUID uuid) {
        attractionFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

}
