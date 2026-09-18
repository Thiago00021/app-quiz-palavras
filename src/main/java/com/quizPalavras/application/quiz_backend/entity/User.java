package com.quizPalavras.application.quiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false,length = 120,unique = true)
    private String email;

    @Column(length = 11)
    private String phoneNumber;

    @Column(unique = true,length = 11)
    private String cpf;

    private LocalDate dateBorn;

    @Column(nullable = false,length = 255)
    private String password;

}
