package ru.geekbrains.aprilmarket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.aprilmarket.entities.CartItem;

import java.math.BigDecimal;


@NoArgsConstructor
@Data
public class CartItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public CartItemDto(CartItem cartItem) {
        this.productId = cartItem.getProductId();
        this.productTitle = cartItem.getTitle();
        this.quantity = cartItem.getQuantity();
        this.pricePerProduct = cartItem.getPricePerProduct();
        this.price = cartItem.getPrice();
    }
}
