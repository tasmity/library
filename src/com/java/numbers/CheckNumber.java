package numbers;

import java.util.ArrayList;
import java.util.Set;

public class CheckNumber {
    private final long number;
    private static final String FORMATNUM = "\t\t\t\t%,d is ";

    public CheckNumber(long number) {
        this.number = number;
    }

    // Boolean methods for checking number parameters
    boolean isEven() {
        return this.number % 2 == 0;
    }

    boolean isBuzz() {
        return this.number % 7 == 0 || String.valueOf(this.number).endsWith("7");
    }

    boolean isDuck() {
        return String.valueOf(this.number).substring(1).contains("0");
    }

    boolean isPalindromic() {
        return String.valueOf(this.number).equals
                (new StringBuilder(String.valueOf(this.number)).reverse().toString());
    }

    boolean isGapful() {
        return String.valueOf(this.number).length() > 2 &&
                this.number % Long.parseLong(String.valueOf(this.number).substring(0, 1) +
                        String.valueOf(this.number).charAt(String.valueOf(this.number).length() - 1)) == 0;
    }

    boolean isSpy() {
        char[] chars = String.valueOf(this.number).toCharArray();
        var s = 0;
        var p = 1;
        for (char ch : chars) {
            s += Character.getNumericValue(ch);
            p *= Character.getNumericValue(ch);
        }
        return s == p;
    }

    boolean isSquare(long num) {
        return Math.sqrt(num) == Math.floor(Math.sqrt(num));
    }

    boolean isSunny() {
        return isSquare(this.number + 1);
    }

    boolean isJumping() {
        char[] chars = String.valueOf(this.number).toCharArray();
        for (var i = 1; i < chars.length; i++) {
            if (Character.getNumericValue(chars[i]) - 1 != Character.getNumericValue(chars[i - 1]) &&
                    Character.getNumericValue(chars[i]) + 1 != Character.getNumericValue(chars[i - 1]))
                return false;
        }
        return true;
    }

    boolean isHappy() {
        ArrayList<Long> listSquare = new ArrayList<>();
        long square = sumSquareDigits(this.number);
        while (true) {
            listSquare.add(square);
            square = sumSquareDigits(square);
            if (square == 1)
                return true;
            for (Long l : listSquare) {
                if (l == square) {
                    return false;
                }
            }
        }
    }

    long sumSquareDigits(long n) {
        if (n < 10)
            return (long) Math.pow(n, 2);
        return (long) ((Math.pow((n % 10), 2)) + sumSquareDigits(n / 10));
    }

    // The method returns a string containing the parameters of the number
    String createPropertiesArray() {
        String tmp = (isEven() ? "even " : "odd ");
        tmp += (isBuzz() ? "buzz " : "");
        tmp += (isDuck() ? "duck " : "");
        tmp += (isPalindromic() ? "palindromic " : "");
        tmp += (isGapful() ? "gapful " : "");
        tmp += (isSpy() ? "spy " : "");
        tmp += (isSquare(this.number) ? "square: " : "");
        tmp += (isSunny() ? "sunny " : "");
        tmp += (isJumping() ? "jumping " : "");
        tmp += (isHappy() ? "happy" : "sad");
        return tmp.replace(" ", ", ");
    }

    // Number output method without parameters
    void printProperties() {
        final var FORM = "%13s %b%n";
        System.out.println("Properties of " + this.number);
        System.out.printf(FORM, "even:", isEven());
        System.out.printf(FORM, "odd:", !isEven());
        System.out.printf(FORM, "buzz:", isBuzz());
        System.out.printf(FORM, "duck:", isDuck());
        System.out.printf(FORM, "palindromic:", isPalindromic());
        System.out.printf(FORM, "gapful:", isGapful());
        System.out.printf(FORM, "spy:", isSpy());
        System.out.printf(FORM, "square:", isSquare(this.number));
        System.out.printf(FORM, "sunny:", isSunny());
        System.out.printf(FORM, "jumping:", isJumping());
        System.out.printf(FORM, "happy:", isHappy());
        System.out.printf(FORM, "sad:", !isHappy());
    }

    // Checking in accordance with the valid parameters
    boolean checkCreateToProperty(String create, Set<String> property) {
        for (String s: property)
            if (!create.contains(s))
                return false;
        return true;
    }

    // Checking compliance with invalid parameters
    boolean checkNotCreateToProperty(String create, Set<String> property) {
        for (String s: property)
            if (create.contains(s))
                return false;
        return true;
    }

    // Prints the number corresponding to the request.
    // The method returns 1 to increment the counter of the range of numbers.
    int printTrueString() {
        System.out.printf(FORMATNUM, this.number);
        System.out.println(createPropertiesArray());
        return 1;
    }

    // The method checks the number for validity.
    // Sends real numbers to print and returns zero if the number is not valid.
    int printPropertiesArray() {
        if (!CheckProperty.validProperties.isEmpty() && !CheckProperty.notProperties.isEmpty()) {
            if (checkCreateToProperty(createPropertiesArray(), CheckProperty.validProperties)
                    && checkNotCreateToProperty(createPropertiesArray(), CheckProperty.notProperties))
                return printTrueString();
        } else if (CheckProperty.notProperties.isEmpty() && !CheckProperty.validProperties.isEmpty()) {
            if (checkCreateToProperty(createPropertiesArray(), CheckProperty.validProperties))
                return printTrueString();
        } else if (!CheckProperty.notProperties.isEmpty()) {
            if (checkNotCreateToProperty(createPropertiesArray(), CheckProperty.notProperties))
                return printTrueString();
        } else
            return printTrueString();
        return 0;
    }
}