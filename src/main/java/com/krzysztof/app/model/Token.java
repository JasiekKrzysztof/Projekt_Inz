package com.krzysztof.app.model;

import javax.persistence.*;

@Entity
public class Token {

    /**
     * id automatycznie inkrementujące
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Answer answer;

    String token;

    /**
     * konstruktor bezparametrowy
     */
    public Token() {
    }

    /**
     * konstruktor
     * @param answer przyjmuje odniesienie do tabeli odpowiedzi
     * @param token przyjmuje token
     */
    public Token(Answer answer, String token) {
        this.answer = answer;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
