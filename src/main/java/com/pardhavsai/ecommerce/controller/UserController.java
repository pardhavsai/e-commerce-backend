package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.User;
import com.pardhavsai.ecommerce.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository repository;
    public UserController(UserRepository repository){this.repository=repository;}
    @GetMapping public List<User> getAll(){return repository.findAll();}
    @GetMapping("/{id}") public User get(@PathVariable Long id){return repository.findById(id).orElseThrow();}
    @PostMapping public User create(@RequestBody User user){return repository.save(user);}
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repository.deleteById(id);}
}
