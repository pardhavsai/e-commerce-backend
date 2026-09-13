package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.Product;
import com.pardhavsai.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){this.service=service;}
    @GetMapping public List<Product> getAll(){return service.findAll();}
    @GetMapping("/search") public List<Product> search(@RequestParam String name){return service.search(name);}
    @GetMapping("/{id}") public Product get(@PathVariable Long id){return service.findById(id);}
    @PostMapping public Product create(@Valid @RequestBody Product product){return service.create(product);}
    @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody Product input){return service.update(id,input);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
