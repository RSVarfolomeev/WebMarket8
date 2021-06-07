package ru.geekbrains.aprilmarket.controllers;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.services.FirebaseMessagingService;
import ru.geekbrains.aprilmarket.services.UserService;

//@RestController
@Slf4j
@RequestMapping("/api/v1/firetest")
@AllArgsConstructor
@CrossOrigin("*")
public class PushNotifyTestController {
    private final FirebaseMessagingService firebaseMessagingService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> testPush(@RequestParam String email, @RequestParam String title, @RequestParam String body) throws FirebaseMessagingException {
        User user = userService.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));

        firebaseMessagingService.sendPrivateDeviceNotification(user, title, body);
        return ResponseEntity.ok("Push sent");
    }
}
