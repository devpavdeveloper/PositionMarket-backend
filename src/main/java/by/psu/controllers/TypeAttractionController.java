package by.psu.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/types/attractions")
public class TypeAttractionController {

   /* private final TypeService typeService;

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
    }*/
}
