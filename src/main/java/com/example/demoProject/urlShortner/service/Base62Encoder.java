package com.example.demoProject.urlShortner.service;

import java.math.BigInteger;
import java.util.StringJoiner;

public class Base62Encoder {
    private static final String BASE62_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String base62Encode(String input){
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        char[] inputChars = input.toCharArray();
        StringBuilder base62String = new StringBuilder();

        for(char ch:inputChars){
            int asciiValue= (int) ch;
            base62String.append(BASE62_CHARACTERS.charAt(asciiValue%62));
        }
        return base62String.toString();
    }
}
