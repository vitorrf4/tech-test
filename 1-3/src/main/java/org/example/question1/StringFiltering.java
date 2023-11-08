package org.example.question1;

import java.util.List;

public class StringFiltering {

    public static List<String> filterList(List<String> list) {
        return list.stream().filter(s ->
                        hasExactlyThreeLetters(s) &&
                        startsWithALowercase(s))
                        .toList();
    }

    private static boolean hasExactlyThreeLetters(String s) {
        return s.length() == 3;
    }

    private static boolean startsWithALowercase(String string) {
        return string.startsWith("a");
    }
}
