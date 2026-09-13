package com.pardhavsai.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false) private User user;

    public Cart() {}
    public Cart(User user) { this.user = user; }
    public Long getId() { return id; }
    public User getUser() { return user; }
}
