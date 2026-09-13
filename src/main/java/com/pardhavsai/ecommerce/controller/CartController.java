package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.*;
import com.pardhavsai.ecommerce.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartRepository carts; private final CartItemRepository items; private final UserRepository users; private final ProductRepository products;
    public CartController(CartRepository carts,CartItemRepository items,UserRepository users,ProductRepository products){this.carts=carts;this.items=items;this.users=users;this.products=products;}
    @PostMapping("/{userId}") public Cart create(@PathVariable Long userId){
        User u=users.findById(userId).orElseThrow(); return carts.findByUserId(userId).orElseGet(()->carts.save(new Cart(u)));
    }
    @GetMapping("/{userId}/items") public List<CartItem> view(@PathVariable Long userId){
        Cart c=carts.findByUserId(userId).orElseThrow(); return items.findAll().stream().filter(i->i.getCart().getId().equals(c.getId())).toList();
    }
    @PostMapping("/{userId}/items") public CartItem add(@PathVariable Long userId,@RequestParam Long productId,@RequestParam(defaultValue="1") int quantity){
        if(quantity<1) throw new IllegalArgumentException("Quantity must be positive");
        Cart c=carts.findByUserId(userId).orElseGet(()->carts.save(new Cart(users.findById(userId).orElseThrow())));
        Product p=products.findById(productId).orElseThrow();
        CartItem item=items.findByCartIdAndProductId(c.getId(),productId).orElse(new CartItem(c,p,0)); item.setQuantity(item.getQuantity()+quantity); return items.save(item);
    }
    @DeleteMapping("/items/{itemId}") public void remove(@PathVariable Long itemId){items.deleteById(itemId);}
}
