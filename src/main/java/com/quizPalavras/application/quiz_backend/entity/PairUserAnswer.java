package com.quizPalavras.application.quiz_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pair_user_answer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PairUserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPairAnswer;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "id_pair_ptbr",nullable = false)
    private Pair pairPtbr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pair_enus", nullable = false)
    private Pair pairEnus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private LocalDateTime answeredAt;

    @Transient
    public boolean isCorrect() {
        return pairPtbr.getIdPair().equals(pairEnus.getIdPair());
    }

}
