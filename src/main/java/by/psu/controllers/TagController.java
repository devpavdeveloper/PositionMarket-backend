package by.psu.controllers;

import by.psu.model.Tag;
import by.psu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/tags")
public class TagController {

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
    }
}
