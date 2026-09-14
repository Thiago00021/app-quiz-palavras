package com.quizPalavras.application.quiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pair")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPair;

    @Column(nullable = false,length = 120)
    private String wordPtbr;

    @Column(nullable = false,length = 120)
    private String wordEnus;

}
