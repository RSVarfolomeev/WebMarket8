package ru.geekbrains.aprilmarket.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.aprilmarket.services.UserService;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping(value = "api/v1/users")
@Api(value = "Set of endpoints for profiles")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {

    private UserService userService;

    @GetMapping(value = "/set_firebase_token")
    @ApiOperation("Sets firebase token to user")
    public ResponseEntity<?> setFirebaseToken(@RequestParam String token, Principal principal) {
        userService.setFirebaseToken(principal.getName(), token);
        return new ResponseEntity<>("Token updated", HttpStatus.OK);
    }
}
