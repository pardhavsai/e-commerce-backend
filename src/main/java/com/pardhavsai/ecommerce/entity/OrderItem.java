package com.pardhavsai.ecommerce.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Order order;
    @ManyToOne(optional=false) private Product product;
    private int quantity;
    private double price;
    public OrderItem() {}
    public OrderItem(Order order, Product product, int quantity, double price) { this.order=order; this.product=product; this.quantity=quantity; this.price=price; }
    public Long getId(){return id;} public Order getOrder(){return order;} public Product getProduct(){return product;}
    public int getQuantity(){return quantity;} public double getPrice(){return price;}
}
