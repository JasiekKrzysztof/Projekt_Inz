package com.krzysztof.app.SecurityConfig;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {

    private static BCryptPasswordEncoder INSTANCE = null;

    public static BCryptPasswordEncoder getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BCryptPasswordEncoder();
        return INSTANCE;
    }
}