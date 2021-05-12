package com.java.random;

import java.util.Scanner;

public class GeneratorTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int secretLength = scanner.nextInt();
        if (secretLength > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d because" +
                    "there aren't enough unique digits.%n", secretLength);
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int secretSymbol = scanner.nextInt();

        System.out.print("The secret is prepared: " + Generator.randomSecret(secretLength, secretSymbol));
    }
}
