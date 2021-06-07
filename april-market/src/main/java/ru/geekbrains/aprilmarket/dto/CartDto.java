package ru.geekbrains.aprilmarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.aprilmarket.entities.Cart;


import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class CartDto {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getPrice();
        this.items = cart.getCartItems().stream().map(CartItemDto::new).collect(Collectors.toList());
    }
}
