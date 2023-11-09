package org.example.question2;

public class WhiteSpaceRemover {
    private int firstLetterIndex = -1;
    private int lastLetterIndex = -1;

    public String removeWhiteSpaces(String string) {
        if (!string.contains(" "))
            return string;

        char[] chars = string.toCharArray();
        StringBuilder stringWithoutSpaces = new StringBuilder();

        setFirstLetterIndex(chars);
        setLastLetterIndex(chars);
        if (firstLetterIndex == -1 && lastLetterIndex == -1)
            return "";

        for (int i = firstLetterIndex; i <= lastLetterIndex; i++) {
            if (i == lastLetterIndex) {
                stringWithoutSpaces.append(chars[i]);
                break;
            }

            if (!isDoubleSpace(chars[i], chars[i + 1])) {
                stringWithoutSpaces.append(chars[i]);
            }
        }

        return stringWithoutSpaces.toString();
    }

    private void setFirstLetterIndex(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (!isWhiteSpace(chars[i])){
                firstLetterIndex = i;
                break;
            }
        }
    }

    private void setLastLetterIndex(char[] chars) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (!isWhiteSpace(chars[i])){
                lastLetterIndex = i;
                break;
            }
        }
    }

    private boolean isDoubleSpace(char first, char second) {
        return isWhiteSpace(first) && isWhiteSpace(second);
    }

    private boolean isWhiteSpace(char c) {
        return String.valueOf(c).isBlank();
    }
}
