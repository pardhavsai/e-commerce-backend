package com.pardhavsai.ecommerce.service;

import com.pardhavsai.ecommerce.entity.Product;
import com.pardhavsai.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository){this.repository=repository;}
    public List<Product> findAll(){return repository.findAll();}
    public List<Product> search(String name){return repository.findByNameContainingIgnoreCase(name);}
    public Product findById(Long id){return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found: " + id));}
    public Product create(Product product){return repository.save(product);}
    public Product update(Long id, Product input){
        Product product=findById(id);
        product.setName(input.getName()); product.setPrice(input.getPrice()); product.setStock(input.getStock());
        return repository.save(product);
    }
    public void delete(Long id){repository.deleteById(id);}
}
