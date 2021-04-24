package org.coderead.fizzbuzz;

import org.junit.Assert;
import org.junit.Test;


public class FizzBuzzTest {
    @Test
    public void should_print_itself() {
        assertEqual(1, "1");
        assertEqual(2, "2");
    }

    @Test
    public void should_print_Fizz_given_number_divided_by_3() {
        assertEqual(3, "Fizz");
        assertEqual(6, "Fizz");
    }

    @Test
    public void should_print_Fizz_given_number_contains_3() {
        assertEqual(13, "Fizz");
        assertEqual(31, "Fizz");
    }

    @Test
    public void should_print_Buzz_given_number_divided_by_5() {
        assertEqual(5, "Buzz");
        assertEqual(10, "Buzz");
    }

    @Test
    public void should_print_Buzz_given_number_contains_5() {
        assertEqual(52, "Buzz");
    }

    @Test
    public void should_print_FizzBuzz_given_number_divided_by_3_and_5() {
        assertEqual(15, "FizzBuzz");
        assertEqual(45, "FizzBuzz");
    }

    @Test
    public void should_print_FizzBuzz_given_number_contains_3_and_5() {
        assertEqual(35, "FizzBuzz");
    }

    @Test
    public void should_print_FizzBuzz_given_number_contains_3_and_divided_by_5() {
        assertEqual(310, "FizzBuzz");
    }
    @Test
    public void should_print_FizzBuzz_given_number_divided_by_3_and_contains_5() {
        assertEqual(51, "FizzBuzz");
    }

    private void assertEqual(int number, String expected) {
        Assert.assertEquals(FizzBuzz.of(number), expected);
    }
}
