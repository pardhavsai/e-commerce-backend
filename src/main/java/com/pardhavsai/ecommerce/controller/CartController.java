package com.pardhavsai.ecommerce.controller;

import com.pardhavsai.ecommerce.entity.Cart;
import com.pardhavsai.ecommerce.entity.CartItem;
import com.pardhavsai.ecommerce.service.CartService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService service;
    public CartController(CartService service){this.service=service;}

    @PostMapping("/{userId}")
    public Cart createOrGet(@PathVariable Long userId){return service.getOrCreate(userId);}

    @GetMapping("/{userId}/items")
    public List<CartItem> view(@PathVariable Long userId){return service.view(userId);}

    @PostMapping("/{userId}/items")
    public CartItem add(@PathVariable Long userId, @RequestParam Long productId,
                        @RequestParam(defaultValue="1") int quantity){
        return service.add(userId, productId, quantity);
    }

    @PatchMapping("/items/{itemId}")
    public CartItem update(@PathVariable Long itemId, @RequestParam int quantity){return service.update(itemId, quantity);}

    @DeleteMapping("/items/{itemId}")
    public void remove(@PathVariable Long itemId){service.remove(itemId);}
}
