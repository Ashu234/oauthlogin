package com.oauth.login.util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    private static final PasswordEncoder bcrypt = new BCryptPasswordEncoder();


    public static String generateBCrypt(String password) {

        if (password == null) {
            return password;
        }

        return bcrypt.encode(password);

    }

    public static boolean verifyPassword(String password, String encodedPassword) {
        if (password == null || encodedPassword == null) {
            return false;
        }

        return bcrypt.matches(password , encodedPassword);

    }
}
