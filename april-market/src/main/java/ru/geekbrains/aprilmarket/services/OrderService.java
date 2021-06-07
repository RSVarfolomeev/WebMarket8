package ru.geekbrains.aprilmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.aprilmarket.entities.Cart;
import ru.geekbrains.aprilmarket.entities.Order;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.exception.ResourceNotFoundException;
import ru.geekbrains.aprilmarket.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private UserService userService;
    private CartService cartService;
    private OrderRepository orderRepository;

    @Transactional
    public Order createFromUserCart(String username, String address) {
        User user = userService.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartService.findCartByOwnerId(user.getId());
        if (cart.getCartItems().isEmpty()) throw new RuntimeException("Cart is empty");
        Order order = new Order(cart, address, user);
        order = orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllByOwner(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
