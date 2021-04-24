package org.coderead.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;


public class FizzBuzzTest {
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";
    private static final String FIZZBUZZ = "FizzBuzz";


    @Test
    public void should_print_itself() {
        assertEqual(1, "1");
        assertEqual(2, "2");
    }

    @Test
    public void should_print_Fizz_given_number_divided_by_3() {
        assertEqual(3, FIZZ);
        assertEqual(6, FIZZ);
    }

    @Test
    public void should_print_Fizz_given_number_contains_3() {
        assertEqual(13, FIZZ);
        assertEqual(31, FIZZ);
    }

    @Test
    public void should_print_Buzz_given_number_divided_by_5() {
        assertEqual(5, BUZZ);
        assertEqual(10, BUZZ);
    }

    @Test
    public void should_print_Buzz_given_number_contains_5() {
        assertEqual(52, BUZZ);
    }

    @Test
    public void should_print_FizzBuzz_given_number_divided_by_3_and_5() {
        assertEqual(15, FIZZBUZZ);
        assertEqual(45, FIZZBUZZ);
    }

    @Test
    public void should_print_FizzBuzz_given_number_contains_3_and_5() {
        assertEqual(35, FIZZBUZZ);
    }

    @Test
    public void should_print_FizzBuzz_given_number_contains_3_and_divided_by_5() {
        assertEqual(310, FIZZBUZZ);
    }

    @Test
    public void should_print_FizzBuzz_given_number_divided_by_3_and_contains_5() {
        assertEqual(51, FIZZBUZZ);
    }

    private void assertEqual(int number, String expected) {
        Assert.assertEquals(FizzBuzz.of(number), expected);
    }
}
