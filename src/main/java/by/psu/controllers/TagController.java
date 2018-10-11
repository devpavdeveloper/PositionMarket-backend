package by.psu.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/tags")
public class TagController {
/*
    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> get() {
        return ResponseEntity.ok(tagService.findAll());
    }

    @PostMapping
    public ResponseEntity<Set<Tag>> createAllTags(@RequestBody Tag[] tags) {
        return ResponseEntity.ok(tagService.saveOrFind(new ArrayList<>(Arrays.asList(tags))));
    }*/
}
