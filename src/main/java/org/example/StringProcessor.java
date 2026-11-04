package org.example;

public class StringProcessor {
    public static String repeatString(String s, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Количество повторений не может быть отрицательным");
        }
        String result = "";
        for (int i = 0; i < n; i++){
            result += s;
        }
            return result;
    }

    public static int countOccurrences(String text, String part) {
        if (part == null || part.isEmpty()) {
            throw new IllegalArgumentException("Искомая строка не должна быть пустой");
        }

        int count = 0;
        int position = 0;

        while ((position = text.indexOf(part, position)) != -1) {
            count++;
            position++;
        }

        return count;
    }

    public static String replaceDigits(String text) {

        String result = text.replace("1", "один");
        result = result.replace("2", "два");
        result = result.replace("3", "три");

        return result;
    }

    public static void removeEverySecond(StringBuilder text) {
        for (int i = text.length() - 1; i > 0; i--) {
            if(i % 2 == 1){
                text.deleteCharAt(i);
            }
        }
    }

    public static String reverseWords(String text) {
        if (text.trim().isEmpty()) {
            return text;
        }

        String[] words = text.trim().split(" +");

        StringBuilder result = new StringBuilder();
        int wordIndex = words.length - 1;

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == ' ') {
                result.append(' ');
            } else if (i == 0 || text.charAt(i-1) == ' ') {
                result.append(words[wordIndex]);
                wordIndex--;
            }
        }

        return text; // пока временно, чтобы метод не был красным
    }

}
