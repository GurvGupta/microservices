package com.example.user_service.controller;
import com.example.user_service.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String username, @RequestParam String password) {
        return service.validate(username, password) ? "Login successful [POST]" : "Invalid credentials [POST]";
    }

}