package com.quizPalavras.application.quiz_backend.dto.auth;

public record AuthResponse(
        String token,
        String name,
        String email
) {
}
