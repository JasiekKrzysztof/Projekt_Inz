package com.krzysztof.app.SecurityConfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {

    /**
     * przypisanie wartości null do BCryptPasswordEncodera
     */
    private static BCryptPasswordEncoder INSTANCE = null;

    /**
     * metoda sprawdza czy jest już utworzona instacja enkodera
     * @return zwraca nową instaancję lub istniejącą
     */
    public static BCryptPasswordEncoder getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BCryptPasswordEncoder();
        return INSTANCE;
    }
}