package com.teatro.controller;

import com.teatro.dto.LoginRequest;
import com.teatro.dto.RegisterRequest;
import com.teatro.dto.UtenteDto;
import com.teatro.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UtenteDto> register(@Valid @RequestBody RegisterRequest req) {
        return ResponseEntity.ok(UtenteDto.from(service.register(req)));
    }

    @PostMapping("/login")
    public ResponseEntity<UtenteDto> login(@Valid @RequestBody LoginRequest req) {
        return ResponseEntity.ok(UtenteDto.from(service.login(req)));
    }
}
