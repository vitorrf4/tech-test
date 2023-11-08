package org.example.question3;

import java.util.Arrays;

public class NumberSwapper {
    public static int[] swapTwoNumbers(int[] numbers) {
        var firstNumber = numbers[0];
        var secondNumber = numbers[1];

        numbers = Arrays.stream(numbers).map(n -> {
            if (n == firstNumber) {
                return secondNumber;
            } else {
                return firstNumber;
            }
        }).toArray();

        return numbers;
    }
}
