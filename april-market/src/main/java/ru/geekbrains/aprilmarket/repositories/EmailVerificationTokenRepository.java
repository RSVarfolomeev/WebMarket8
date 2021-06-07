package ru.geekbrains.aprilmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.aprilmarket.entities.EmailVerificationToken;
import ru.geekbrains.aprilmarket.entities.User;

@Repository
public interface EmailVerificationTokenRepository extends JpaRepository<EmailVerificationToken, Long> {
    EmailVerificationToken findByToken(String token);
    EmailVerificationToken findByUser(User user);
}
