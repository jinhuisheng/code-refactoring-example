package org.coderead.fizzbuzz;

import static java.lang.String.*;

public class FizzBuzz {
    private int number;

    private FizzBuzz(int number) {
        this.number = number;
    }

    public static String of(int number) {
        FizzBuzz fizzBuzz = new FizzBuzz(number);
        return fizzBuzz.parse();
    }

    private String parse() {
        String result = "";
        if (isFizz()) {
            result += "Fizz";
        }
        if (isBuzz()) {
            result += "Buzz";
        }
        return result.isEmpty() ? valueOf(number) : result;
    }

    private boolean isFizz() {
        return dividedBy(3) || contains(3);
    }

    private boolean isBuzz() {
        return dividedBy(5) || contains(5);
    }

    private boolean contains(int i) {
        return valueOf(number).contains(valueOf(i));
    }

    private boolean dividedBy(int i) {
        return number % i == 0;
    }
}
