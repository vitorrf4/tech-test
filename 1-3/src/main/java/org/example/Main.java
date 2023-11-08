package org.example;

import org.example.exercise1.StringFiltering;
import org.example.exercise2.WhiteSpaceRemover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        exerciseTwoSampleTest();
    }

    public static void exerciseOneSampleTest() {
        String[] testStrings = {"AAA", "aaa", "test", "box", "ape", "alex", "bit", "Aaa", "", "alt"};

        List<String> stringList = Arrays.asList(testStrings);

        List<String> filteredStringList = StringFiltering.filterList(stringList);

        System.out.println("Initial list:");
        stringList.forEach(System.out::println);

        System.out.println("Filtered list of strings that start with 'a' and have exactly three leters: ");
        filteredStringList.forEach(System.out::println);

    }

    public static void exerciseTwoSampleTest() {
        String[] testStrings = {
                "test",
                "a string",
                "   ",
                " ",
                "StringWithNoSpaces",
                "  string   with lots of spaces    "
        };

        for (String string : testStrings) {
            String stringWithoutWhiteSpaces = WhiteSpaceRemover.removeWhiteSpaces(string);

            System.out.println("String before: " + string);
            System.out.println("String with no whitespaces: " + stringWithoutWhiteSpaces);
        }

    }
}