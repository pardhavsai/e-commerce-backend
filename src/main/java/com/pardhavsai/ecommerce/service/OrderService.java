package com.pardhavsai.ecommerce.service;

import com.pardhavsai.ecommerce.entity.*;
import com.pardhavsai.ecommerce.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orders;
    private final CartService cartService;
    private final CartItemRepository cartItems;
    private final ProductRepository products;
    private final UserService users;

    public OrderService(OrderRepository orders, CartService cartService, CartItemRepository cartItems,
                        ProductRepository products, UserService users){
        this.orders=orders; this.cartService=cartService; this.cartItems=cartItems; this.products=products; this.users=users;
    }

    public List<Order> findAll(){return orders.findAll();}
    public Order findById(Long id){return orders.findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));}
    public List<Order> findByUser(Long userId){users.findById(userId); return orders.findByUserId(userId);}

    @Transactional
    public Order createFromCart(Long userId){
        User user=users.findById(userId);
        Cart cart=cartService.getOrCreate(userId);
        List<CartItem> cartList=cartItems.findByCartId(cart.getId());
        if(cartList.isEmpty()) throw new IllegalArgumentException("Cannot create an order from an empty cart");

        Order order=new Order(user, "PLACED", 0);
        double total=0;
        for(CartItem cartItem: cartList){
            Product product=cartItem.getProduct();
            int quantity=cartItem.getQuantity();
            if(product.getStock() < quantity) throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            product.setStock(product.getStock()-quantity);
            products.save(product);
            total += product.getPrice()*quantity;
            order.addItem(new OrderItem(order, product, quantity, product.getPrice()));
        }
        order.setTotalAmount(total);
        Order saved=orders.save(order);
        cartItems.deleteAll(cartList);
        return saved;
    }

    public Order updateStatus(Long id, String status){
        Order order=findById(id);
        order.setStatus(status.toUpperCase());
        return orders.save(order);
    }
}
