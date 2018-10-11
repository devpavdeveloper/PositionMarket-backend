package by.psu.controllers;

import by.psu.model.postgres.Tag;
import by.psu.model.postgres.repository.RepositoryAttraction;
import by.psu.service.api2.ServiceTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final ServiceTag serviceTag;

    @Autowired
    public TagController(ServiceTag serviceTag) {
        this.serviceTag = serviceTag;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> get() {
        return ResponseEntity.ok(serviceTag.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> get(@PathVariable UUID id) {
        return ResponseEntity.ok(serviceTag.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Tag> get(@RequestBody Tag tag) {
        return ResponseEntity.ok(serviceTag.saveOrFind(tag));
    }

    @PutMapping
    public ResponseEntity<Tag> update(@RequestBody Tag tag) {
        return ResponseEntity.ok(serviceTag.update(tag.getTitle(), tag.getId()));
    }
}
