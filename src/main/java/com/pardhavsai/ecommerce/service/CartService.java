package com.pardhavsai.ecommerce.service;

import com.pardhavsai.ecommerce.entity.*;
import com.pardhavsai.ecommerce.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private final CartRepository carts;
    private final CartItemRepository items;
    private final UserService users;
    private final ProductService products;

    public CartService(CartRepository carts, CartItemRepository items, UserService users, ProductService products){
        this.carts=carts; this.items=items; this.users=users; this.products=products;
    }

    public Cart getOrCreate(Long userId){
        return carts.findByUserId(userId).orElseGet(() -> carts.save(new Cart(users.findById(userId))));
    }
    public List<CartItem> view(Long userId){
        Cart cart=getOrCreate(userId);
        return items.findByCartId(cart.getId());
    }
    public CartItem add(Long userId, Long productId, int quantity){
        if(quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        Cart cart=getOrCreate(userId);
        Product product=products.findById(productId);
        if(product.getStock() < quantity) throw new IllegalArgumentException("Insufficient stock");
        CartItem item=items.findByCartIdAndProductId(cart.getId(), productId).orElse(new CartItem(cart, product, 0));
        int newQuantity=item.getQuantity()+quantity;
        if(newQuantity > product.getStock()) throw new IllegalArgumentException("Quantity exceeds available stock");
        item.setQuantity(newQuantity);
        return items.save(item);
    }
    public CartItem update(Long itemId, int quantity){
        if(quantity < 1) throw new IllegalArgumentException("Quantity must be positive");
        CartItem item=items.findById(itemId).orElseThrow(() -> new RuntimeException("Cart item not found: " + itemId));
        if(quantity > item.getProduct().getStock()) throw new IllegalArgumentException("Quantity exceeds available stock");
        item.setQuantity(quantity); return items.save(item);
    }
    public void remove(Long itemId){items.deleteById(itemId);}
}
