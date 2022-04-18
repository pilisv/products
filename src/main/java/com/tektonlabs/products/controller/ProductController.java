package com.tektonlabs.products.controller;

import com.tektonlabs.products.dto.ProductDTO;
import com.tektonlabs.products.service.ProductService;
import com.tektonlabs.products.util.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/product", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ExecutionTime
    public ResponseEntity<String> create(@Valid @RequestBody ProductDTO productDTO){
        try {
            productService.create(productDTO);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            log.error("Error while saving", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @GetMapping("/{id}")
    @ExecutionTime
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(productService.getById(id));
        }catch (NoSuchElementException e){
            log.error("Error while searching", e);
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            log.error("Error while searching", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    @ExecutionTime
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO){
        try {
            productService.update(id, productDTO);
            return ResponseEntity.ok().build();
        }catch (NoSuchElementException e){
            log.error("Error while updating", e);
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            log.error("Error while updating", e);
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
