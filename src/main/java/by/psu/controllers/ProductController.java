package by.psu.controllers;

import by.psu.facade.ProductFacade;
import by.psu.service.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductFacade productFacade;

    public ProductController(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> get() {
        return ResponseEntity.ok(productFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> get(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(productFacade.getOne(id));
    }

    @PostMapping()
    public ResponseEntity<ProductDTO> create(@RequestBody  ProductDTO obj) {
        return ResponseEntity.ok(productFacade.save(obj));
    }

    @PutMapping()
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO obj) {
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
