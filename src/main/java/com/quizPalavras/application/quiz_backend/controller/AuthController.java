package com.quizPalavras.application.quiz_backend.controller;

import com.quizPalavras.application.quiz_backend.dto.auth.AuthResponse;
import com.quizPalavras.application.quiz_backend.dto.auth.LoginRequest;
import com.quizPalavras.application.quiz_backend.dto.auth.RegisterRequest;
import com.quizPalavras.application.quiz_backend.entity.User;
import com.quizPalavras.application.quiz_backend.repository.UserRepository;
import com.quizPalavras.application.quiz_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me(Authentication authentication) {
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        return ResponseEntity.ok(new AuthResponse(null, user.getName(), user.getEmail()));
    }
}