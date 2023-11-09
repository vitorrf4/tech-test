package org.example.question1;

import java.util.List;

public class StringFiltering {

    public static List<String> filterList(List<String> list) {
        return list.stream().filter(s ->
                        hasExactlyThreeLetters(s) &&
                        startsWithLowercaseA(s))
                        .toList();
    }

    private static boolean hasExactlyThreeLetters(String s) {
        return s.length() == 3;
    }

    private static boolean startsWithLowercaseA(String string) {
        return string.startsWith("a");
    }
}
