package ru.geekbrains.aprilmarket.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.dto.UserRegisterDto;
import ru.geekbrains.aprilmarket.entities.User;
import ru.geekbrains.aprilmarket.services.UserRegisterService;
import ru.geekbrains.aprilmarket.util.OnEmailVerificationCompleteEvent;

@RestController
@Slf4j
@RequestMapping("/register")
@AllArgsConstructor
@CrossOrigin("*")
public class RegistrationController {
    private final UserRegisterService registerService;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody UserRegisterDto dto) {
        User registered = registerService.createNewUser(dto);

        eventPublisher.publishEvent(new OnEmailVerificationCompleteEvent(registered));
        return ResponseEntity.ok("{\"msg\": \"Please verify your email\"}");
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam String token) {
        registerService.validateEmail(token);
        return ResponseEntity.ok("{\"msg\": \"Verify success. Please log in..\"}");
    }
}
