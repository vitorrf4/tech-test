package org.example.exercise1;

import java.util.List;

public class StringFiltering {

    public static List<String> filterList(List<String> list) {
        return list.stream().filter(s ->
                        hasExactlyThreeLetters(s) &&
                        startsWithALowercase(s))
                        .toList();
    }

    public static boolean hasExactlyThreeLetters(String s) {
        return s.length() == 3;
    }

    public static boolean startsWithALowercase(String string) {
        return string.startsWith("a");
    }
}
