package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.User;
import com.pardhavsai.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service){this.service=service;}
    @GetMapping public List<User> getAll(){return service.findAll();}
    @GetMapping("/{id}") public User get(@PathVariable Long id){return service.findById(id);}
    @PostMapping public User create(@Valid @RequestBody User user){return service.create(user);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){service.delete(id);}
}
