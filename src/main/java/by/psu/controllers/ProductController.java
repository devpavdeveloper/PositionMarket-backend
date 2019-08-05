package by.psu.controllers;

import by.psu.facade.ProductFacade;
import by.psu.model.postgres.Product;
import by.psu.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> get() {
        return ResponseEntity.ok(productFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(productFacade.getOne(id).get());
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> create(@RequestBody  Product obj) {
        return ResponseEntity.ok(productFacade.save(obj).get());
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(@RequestBody Product obj) {
        return ResponseEntity.ok(productFacade.update(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") UUID uuid) {
        productFacade.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/[{uuid}]")
    public ResponseEntity delete(@PathVariable UUID[] uuid) {
        productFacade.delete(Arrays.asList(uuid));
        return ResponseEntity.ok().build();
    }

}
