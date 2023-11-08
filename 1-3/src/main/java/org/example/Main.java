package org.example;

import org.example.exercise1.StringFiltering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] testStrings = {"AAA", "aaa", "test", "box", "ape", "alex", "bit", "Aaa", "", "alt"};

        List<String> stringList = Arrays.asList(testStrings);

        List<String> filteredStringList = StringFiltering.filterList(stringList);

        System.out.println("Strings that start with 'a' and have exactly three leterrs: ");
        filteredStringList.forEach(System.out::println);

    }
}