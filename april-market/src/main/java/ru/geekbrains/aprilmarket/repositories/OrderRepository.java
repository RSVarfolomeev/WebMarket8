package ru.geekbrains.aprilmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.aprilmarket.entities.Order;
import ru.geekbrains.aprilmarket.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOwnerUsername(String ownerUsername);
}
