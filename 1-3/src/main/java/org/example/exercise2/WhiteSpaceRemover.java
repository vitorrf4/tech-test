package org.example.exercise2;

public class WhiteSpaceRemover {
    public static String removeWhiteSpaces(String string) {
        if (!string.contains(" ")) {
            return "";
        }

        char[] chars = string.toCharArray();
        StringBuilder stringWithoutSpaces = new StringBuilder();

        for (char character : chars) {
            if (!isWhiteSpace(character))
                stringWithoutSpaces.append(character);
        }

        return stringWithoutSpaces.toString();
    }

    private static boolean isWhiteSpace(char c) {
        return String.valueOf(c).isBlank();
    }
}
