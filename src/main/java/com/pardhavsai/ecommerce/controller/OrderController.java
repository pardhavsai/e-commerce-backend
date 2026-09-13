package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.Order;
import com.pardhavsai.ecommerce.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderRepository repository;
    public OrderController(OrderRepository repository){this.repository=repository;}
    @GetMapping public List<Order> getAll(){return repository.findAll();}
    @GetMapping("/{id}") public Order get(@PathVariable Long id){return repository.findById(id).orElseThrow();}
    @GetMapping("/user/{userId}") public List<Order> byUser(@PathVariable Long userId){return repository.findByUserId(userId);}
    @PatchMapping("/{id}/status") public Order updateStatus(@PathVariable Long id,@RequestParam String status){Order o=repository.findById(id).orElseThrow();o.setStatus(status);return repository.save(o);}
}
