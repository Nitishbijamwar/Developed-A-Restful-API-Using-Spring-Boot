package com.training.app.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder {

    private CustomPasswordEncoder() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static Boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
	
}
