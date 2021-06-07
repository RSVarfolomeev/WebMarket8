package ru.geekbrains.aprilmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.aprilmarket.entities.Cart;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
//    Optional<Cart> findByOwnerId(Long id);
}
