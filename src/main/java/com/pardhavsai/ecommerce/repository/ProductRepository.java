package com.pardhavsai.ecommerce.repository;
import com.pardhavsai.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> { List<Product> findByNameContainingIgnoreCase(String name); }
