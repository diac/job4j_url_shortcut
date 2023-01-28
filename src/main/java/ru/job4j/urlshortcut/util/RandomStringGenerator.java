package ru.job4j.urlshortcut.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomStringGenerator implements StringGenerator {

    private static final int MIN_LENGTH = 16;

    private static final String CHARS = "1234567890-=!@#$%^&*()_+abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ,.:;/\\|";

    @Override
    public char[] generate() {
        return generate(MIN_LENGTH);
    }

    @Override
    public char[] generate(int length) {
        Random random = new Random();
        char[] randomString = new char[length];
        for (int i = 0; i < length; i++) {
            randomString[i] = CHARS.charAt(random.nextInt(0, CHARS.length() - 1));
        }
        return randomString;
    }
}