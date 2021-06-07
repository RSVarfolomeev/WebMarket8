package ru.geekbrains.aprilmarket.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.dto.CartDto;
import ru.geekbrains.aprilmarket.entities.Cart;
import ru.geekbrains.aprilmarket.entities.CartItem;
import ru.geekbrains.aprilmarket.entities.Product;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.services.CartService;
import ru.geekbrains.aprilmarket.services.ProductService;
import ru.geekbrains.aprilmarket.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
@CrossOrigin("*")
@Slf4j
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public CartDto getCurrentCart(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = cartService.findCartByOwnerId(user.getId());
        log.info("{}", cart);
        return new CartDto(cart);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProductToCart(Principal principal, @RequestParam(name = "product_id") Long productId) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        cartService.addToCart(user.getId(), productId);
        return ResponseEntity.ok("");
    }

    @PostMapping
    public Cart updateCart(@RequestBody Cart cart) {
       return cartService.updateCart(cart);
    }

    @DeleteMapping
    public CartDto clearCart(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
       Cart cart = cartService.clearCart(user.getId());
       return new CartDto(cart);
    }


//    @GetMapping("/mock")
//    public Cart getMockCart(Principal principal) {
////        if (principal == null) {
////            return cartService.getCartForUser(null, null);
////        }
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        Cart cart = new Cart();
//        List<CartItem> items = new ArrayList<>();
//        List<Product> products = productService.getAllProducts();
//        for (Product product : products) {
//            items.add(new CartItem(product));
//        }
//        cart.setOwnerId(1L);
//        cart.setCartItems(items);
//
//        return cart;
//    }
}
