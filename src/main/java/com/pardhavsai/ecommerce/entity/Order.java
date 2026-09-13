package com.pardhavsai.ecommerce.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional = false) private User user;
    @Column(nullable = false) private String status;
    @Column(nullable = false) private double totalAmount;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}
    public Order(User user, String status, double totalAmount) { this.user=user; this.status=status; this.totalAmount=totalAmount; }
    public Long getId(){return id;} public User getUser(){return user;} public String getStatus(){return status;}
    public double getTotalAmount(){return totalAmount;} public List<OrderItem> getItems(){return items;}
    public void setStatus(String status){this.status=status;} public void setTotalAmount(double totalAmount){this.totalAmount=totalAmount;}
    public void addItem(OrderItem item){items.add(item);}
}
