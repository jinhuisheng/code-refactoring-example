package org.coderead.fizzbuzz;

import static java.lang.String.*;

public class FizzBuzz {
    public static final int FIZZ_NUMBER = 3;
    public static final int BUZZ_NUMBER = 5;
    private final int number;

    private FizzBuzz(int number) {
        this.number = number;
    }

    public static String of(int number) {
        FizzBuzz fizzBuzz = new FizzBuzz(number);
        return fizzBuzz.parse();
    }

    private String parse() {
        String result = "";
        if (isDividedByOrContains(FIZZ_NUMBER)) {
            result += "Fizz";
        }
        if (isDividedByOrContains(BUZZ_NUMBER)) {
            result += "Buzz";
        }
        return result.isEmpty() ? valueOf(number) : result;
    }

    private boolean isDividedByOrContains(int number) {
        return dividedBy(number) || contains(number);
    }

    private boolean contains(int i) {
        return valueOf(number).contains(valueOf(i));
    }

    private boolean dividedBy(int i) {
        return number % i == 0;
    }
}
