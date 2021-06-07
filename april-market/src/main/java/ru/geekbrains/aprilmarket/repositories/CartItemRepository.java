package ru.geekbrains.aprilmarket.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.aprilmarket.entities.CartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findAllByCart_Id(Long id);
    Optional<CartItem> findCartItemByProductId(Long productId);
}
