package com.pardhavsai.ecommerce.service;

import com.pardhavsai.ecommerce.entity.User;
import com.pardhavsai.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository){this.repository=repository;}
    public List<User> findAll(){return repository.findAll();}
    public User findById(Long id){return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found: " + id));}
    public User create(User user){return repository.save(user);}
    public void delete(Long id){repository.deleteById(id);}
}
