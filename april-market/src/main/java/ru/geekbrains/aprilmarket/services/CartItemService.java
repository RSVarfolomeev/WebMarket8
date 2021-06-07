package ru.geekbrains.aprilmarket.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.aprilmarket.entities.Cart;
import ru.geekbrains.aprilmarket.entities.CartItem;
import ru.geekbrains.aprilmarket.exception.ResourceNotFoundException;
import ru.geekbrains.aprilmarket.repositories.CartItemRepository;
import ru.geekbrains.aprilmarket.repositories.CartRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class CartItemService {
    private CartItemRepository cartItemRepository;
    private ProductService productService;

    public CartItem addToCart(Cart cart, Long productId) {
        CartItem cartItem = new CartItem(productService.findProductById(productId).orElseThrow(() -> new ResourceNotFoundException("No such product")));
        cartItem.setCart(cart);
        log.info("Add item to cart {}:{}", cartItem.getTitle(), cartItem.getQuantity());
         return cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> findById(Long id) {
       return cartItemRepository.findById(id);
    }

    public Optional<CartItem> findByProductId(Long id) {
       return cartItemRepository.findCartItemByProductId(id);
    }

    public CartItem saveOrUpdate(CartItem item) {
        return cartItemRepository.save(item);
    }

//    public void recalculateItem(CartItem item) {
//        item.set
//    }

}
