package ru.job4j.urlshortcut.util;

import java.util.Random;

public class Passwords {

    private static final int MIN_LENGTH = 16;

    private static final String CHARS = "1234567890-=!@#$%^&*()_+abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,.:;/\\|";

    private Passwords() {
    }

    public static char[] generate() {
        return generate(MIN_LENGTH);
    }

    public static char[] generate(int length) {
        Random random = new Random();
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = CHARS.charAt(random.nextInt(0, CHARS.length() - 1));
        }
        return password;
    }
}