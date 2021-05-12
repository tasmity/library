package com.java.random;

import java.util.Random;

public class Generator {
    private Generator() {
        throw new IllegalStateException("Utility class");
    }
    static String symbol = "0123456789abcdefghijklmnopqrstuvwxyz";
    static Random random = new Random();
    static String randomSecret(int secretLength, int secretSymbol) {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < secretLength) {
            char c = symbol.charAt(random.nextInt(secretSymbol - 1));
            if (sb.indexOf(String.valueOf(c)) == -1)
                sb.append(c);
        }
        return sb.toString();
    }
}
