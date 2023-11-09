package org.example;

import org.example.question1.StringFiltering;
import org.example.question2.WhiteSpaceRemover;
import org.example.question3.NumberSwapper;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        questionOneSampleTest();
        questionTwoSampleTest();
//        questionThreeSampleTest();
    }

    public static void questionThreeSampleTest() {
        int[][] numbersSample = {
                {1, 2},
                {35, 7},
                {333, 999},
                {4, 4},
                {10, 20}
        };

        for (int[] numberPair : numbersSample) {
            System.out.printf("Inital position: %d, %d\n", numberPair[0], numberPair[1]);
            numberPair = NumberSwapper.swapTwoNumbers(numberPair);
            System.out.printf("After swapping: %d, %d\n", numberPair[0], numberPair[1]);
        }
    }

    public static void questionTwoSampleTest() {
        String[] testStrings = {
                "test",
                "a string",
                "string with regular spaces",
                "   ",
                " ",
                "StringWithNoSpaces",
                "  string   with lots of spaces    ",
                "\n string that starts with \\n  "
        };

        for (String string : testStrings) {
            var remover = new WhiteSpaceRemover();
            String stringWithoutWhiteSpaces = remover.removeWhiteSpaces(string);

            System.out.println("String before: " + string + " | length: " + string.length());
            System.out.println("String with no whitespaces: " + stringWithoutWhiteSpaces + " | length: " + stringWithoutWhiteSpaces.length());
        }

    }

    public static void questionOneSampleTest() {
        String[] testStrings = {"AAA", "aaa", "test", "box", "ape", "alex", "bit", "Aaa", "", "alt"};

        List<String> stringList = Arrays.asList(testStrings);

        List<String> filteredStringList = StringFiltering.filterList(stringList);

        System.out.println("Initial list:");
        stringList.forEach(System.out::println);

        System.out.println("Filtered list of strings that start with 'a' and have exactly three leters: ");
        filteredStringList.forEach(System.out::println);

    }

}