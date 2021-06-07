package ru.geekbrains.aprilmarket.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.dto.OrderDto;
import ru.geekbrains.aprilmarket.entities.Order;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.services.CartService;
import ru.geekbrains.aprilmarket.services.OrderService;
import ru.geekbrains.aprilmarket.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class OrderController {
    @Value("${spring.rabbitmq.send-order-queue: market.orders}")
    private String orderQueueName;

    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) throws JsonProcessingException {
        Order order = orderService.createFromUserCart(principal.getName(), address);
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        cartService.clearCart(user.getId());
        OrderDto dto = new OrderDto(order);
        rabbitTemplate.convertAndSend(orderQueueName, objectMapper.writeValueAsString(dto));
        return dto;
    }

    @GetMapping("/{id}")

    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderDto(order);
    }

    @GetMapping

    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllByOwner(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
