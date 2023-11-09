package org.example.question2;

public class WhiteSpaceRemover {
    public static String removeWhiteSpaces(String string) {
        if (!string.contains(" "))
            return string;

        char[] chars = string.toCharArray();
        StringBuilder stringWithoutSpaces = new StringBuilder();

        int firstLetterIndex = -1;
        for (int i = 0; i < chars.length; i++) {
            if (isWhiteSpace(chars[i]) && firstLetterIndex == -1)
                continue;

            if (i != chars.length - 1 && isDoubleSpace(chars[i], chars[i + 1]))
                continue;

            if (!isWhiteSpace(chars[i]) && firstLetterIndex == -1)
                firstLetterIndex = i;

            stringWithoutSpaces.append(chars[i]);
        }

        return stringWithoutSpaces.toString();
    }

    private static boolean isDoubleSpace(char first, char second) {
        return isWhiteSpace(first) && isWhiteSpace(second);
    }

    private static boolean isWhiteSpace(char c) {
        return String.valueOf(c).isBlank();
    }
}
