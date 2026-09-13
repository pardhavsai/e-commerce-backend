package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.Order;
import com.pardhavsai.ecommerce.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){this.service=service;}

    @GetMapping public List<Order> getAll(){return service.findAll();}
    @GetMapping("/{id}") public Order get(@PathVariable Long id){return service.findById(id);}
    @GetMapping("/user/{userId}") public List<Order> byUser(@PathVariable Long userId){return service.findByUser(userId);}

    @PostMapping("/checkout/{userId}")
    public Order checkout(@PathVariable Long userId){return service.createFromCart(userId);}

    @PatchMapping("/{id}/status")
    public Order updateStatus(@PathVariable Long id,@RequestParam String status){return service.updateStatus(id,status);}
}
