package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.Product;
import com.pardhavsai.ecommerce.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository repository;
    public ProductController(ProductRepository repository){this.repository=repository;}
    @GetMapping public List<Product> getAll(){return repository.findAll();}
    @GetMapping("/search") public List<Product> search(@RequestParam String name){return repository.findByNameContainingIgnoreCase(name);}
    @GetMapping("/{id}") public Product get(@PathVariable Long id){return repository.findById(id).orElseThrow();}
    @PostMapping public Product create(@RequestBody Product product){return repository.save(product);}
    @PutMapping("/{id}") public Product update(@PathVariable Long id,@RequestBody Product input){
        Product p=repository.findById(id).orElseThrow(); p.setName(input.getName()); p.setPrice(input.getPrice()); p.setStock(input.getStock()); return repository.save(p);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repository.deleteById(id);}
}
