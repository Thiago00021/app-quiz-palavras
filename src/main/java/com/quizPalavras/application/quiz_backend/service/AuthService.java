package com.quizPalavras.application.quiz_backend.service;

import com.quizPalavras.application.quiz_backend.dto.auth.AuthResponse;
import com.quizPalavras.application.quiz_backend.dto.auth.LoginRequest;
import com.quizPalavras.application.quiz_backend.dto.auth.RegisterRequest;
import com.quizPalavras.application.quiz_backend.entity.User;
import com.quizPalavras.application.quiz_backend.repository.UserRepository;
import com.quizPalavras.application.quiz_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Este e-mail já está sendo usado.");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPhoneNumber(request.phoneNumber());
        user.setCpf(request.cpf());
        user.setDateBorn(request.dateBorn());

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token, user.getName(), user.getEmail());

    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos"));
        boolean passwordMatches = passwordEncoder.matches(request.password(), user.getPassword());
        if (!passwordMatches){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"E-mail ou senha inválidos");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getName(), user.getEmail());
    }

}
