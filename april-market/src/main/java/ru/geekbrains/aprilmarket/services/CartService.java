package ru.geekbrains.aprilmarket.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.aprilmarket.entities.Cart;
import ru.geekbrains.aprilmarket.entities.CartItem;
import ru.geekbrains.aprilmarket.exception.ResourceNotFoundException;
import ru.geekbrains.aprilmarket.repositories.CartRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CartService {
    private CartRepository cartRepository;
    private ProductService productService;


    public Cart updateCart(Cart cart) {
        recalculateCart(cart);
        return cartRepository.save(cart);
    }

    public Cart findCartByOwnerId(Long id) {
        Cart cart = cartRepository.findById(id).orElse(new Cart(id));
        return cartRepository.save(cart);
    }

    public Cart clearCart(Long id) {
        Cart cart = findCartByOwnerId(id);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    private void recalculateCart(Cart cart) {
        cart.setPrice(new BigDecimal("0.0"));
        for (CartItem cartItem : cart.getCartItems()) {
            cart.setPrice(cart.getPrice().add(cartItem.getPrice()));
        }
    }

    public void addToCart(Long userId, Long productId) {
        log.debug("Adding to cart");
        Cart cart = findCartByOwnerId(userId);
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProductId().equals(productId)) {
                log.debug("Found existing item {}", cartItem.getTitle());
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                recalculateCart(cart);
                cartRepository.save(cart);
                log.debug("Add item to cart {}:{}", cartItem.getTitle(), cartItem.getQuantity());
                return;
            }
        }
        CartItem item = new CartItem(productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("Not found!")));
        cart.getCartItems().add(item);
        recalculateCart(cart);
        cartRepository.save(cart);
    }
}
