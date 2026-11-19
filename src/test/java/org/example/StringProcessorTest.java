package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringProcessorTest {

    // 1. Повторение строки

    @Test
    void repeatStringRepeatsSeveralTimes() {
        assertEquals("abcabcabc", StringProcessor.repeatString("abc", 3));
    }

    @Test
    void repeatStringRepeatsOnce() {
        assertEquals("abc", StringProcessor.repeatString("abc", 1));
    }

    @Test
    void repeatStringReturnsEmptyForZero() {
        assertEquals("", StringProcessor.repeatString("abc", 0));
    }

    @Test
    void repeatStringKeepsEmptyStringEmpty() {
        assertEquals("", StringProcessor.repeatString("", 5));
    }

    @Test
    void repeatStringRejectsNegativeNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.repeatString("abc", -1));
    }

    // 2. Подсчёт вхождений

    @Test
    void countOccurrencesCountsSeveralMatches() {
        assertEquals(3, StringProcessor.countOccurrences("кот кот кот", "кот"));
    }

    @Test
    void countOccurrencesReturnsZeroWhenNothingFound() {
        assertEquals(0, StringProcessor.countOccurrences("кот", "пёс"));
    }

    @Test
    void countOccurrencesWorksWithEmptyText() {
        assertEquals(0, StringProcessor.countOccurrences("", "а"));
    }

    @Test
    void countOccurrencesCountsOverlappingMatches() {
        assertEquals(3, StringProcessor.countOccurrences("aaaa", "aa"));
    }

    @Test
    void countOccurrencesCountsWholeText() {
        assertEquals(1, StringProcessor.countOccurrences("привет", "привет"));
    }

    @Test
    void countOccurrencesRejectsEmptyPart() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.countOccurrences("текст", ""));
    }

    @Test
    void countOccurrencesRejectsNullPart() {
        assertThrows(IllegalArgumentException.class,
                () -> StringProcessor.countOccurrences("текст", null));
    }

    // 3. Замена цифр 1, 2, 3

    @Test
    void replaceDigitsReplacesAllThreeDigits() {
        assertEquals("один два три", StringProcessor.replaceDigits("1 2 3"));
    }

    @Test
    void replaceDigitsReplacesRepeatedDigits() {
        assertEquals("одинодин двадва", StringProcessor.replaceDigits("11 22"));
    }

    @Test
    void replaceDigitsKeepsOtherCharacters() {
        assertEquals("0 один 4 кот", StringProcessor.replaceDigits("0 1 4 кот"));
    }

    @Test
    void replaceDigitsWorksWithEmptyString() {
        assertEquals("", StringProcessor.replaceDigits(""));
    }

    // 4. Удаление каждого второго символа

    @Test
    void removeEverySecondWorksWithOddLength() {
        StringBuilder text = new StringBuilder("abcde");

        StringProcessor.removeEverySecond(text);

        assertEquals("ace", text.toString());
    }

    @Test
    void removeEverySecondWorksWithEvenLength() {
        StringBuilder text = new StringBuilder("abcdef");

        StringProcessor.removeEverySecond(text);

        assertEquals("ace", text.toString());
    }

    @Test
    void removeEverySecondWorksWithOneCharacter() {
        StringBuilder text = new StringBuilder("a");

        StringProcessor.removeEverySecond(text);

        assertEquals("a", text.toString());
    }

    @Test
    void removeEverySecondWorksWithEmptyBuilder() {
        StringBuilder text = new StringBuilder();

        StringProcessor.removeEverySecond(text);

        assertEquals("", text.toString());
    }

    // 5. Обратный порядок слов с сохранением пробелов

    @Test
    void reverseWordsReversesSimpleSentence() {
        assertEquals("три два один",
                StringProcessor.reverseWords("один два три"));
    }

    @Test
    void reverseWordsPreservesSpacesBetweenWords() {
        assertEquals("  dd  cc bbb aaa ",
                StringProcessor.reverseWords("  aaa  bbb cc dd "));
    }

    @Test
    void reverseWordsPreservesOnlySpaces() {
        assertEquals("    ", StringProcessor.reverseWords("    "));
    }

    @Test
    void reverseWordsWorksWithEmptyString() {
        assertEquals("", StringProcessor.reverseWords(""));
    }

    @Test
    void reverseWordsPreservesSingleWord() {
        assertEquals("  привет  ",
                StringProcessor.reverseWords("  привет  "));
    }

    // 6. Замена восьмизначных шестнадцатеричных чисел

    @Test
    void replaceHexNumbersConvertsNumberFromExample() {
        assertEquals("Васе 16 лет",
                StringProcessor.replaceHexNumbers("Васе 0x00000010 лет"));
    }

    @Test
    void replaceHexNumbersConvertsSeveralNumbers() {
        assertEquals("16 и 255",
                StringProcessor.replaceHexNumbers("0x00000010 и 0x000000FF"));
    }

    @Test
    void replaceHexNumbersAcceptsLowercaseDigits() {
        assertEquals("255", StringProcessor.replaceHexNumbers("0x000000ff"));
    }

    @Test
    void replaceHexNumbersConvertsZero() {
        assertEquals("0", StringProcessor.replaceHexNumbers("0x00000000"));
    }

    @Test
    void replaceHexNumbersConvertsMaximumEightDigitNumber() {
        assertEquals("4294967295",
                StringProcessor.replaceHexNumbers("0xFFFFFFFF"));
    }

    @Test
    void replaceHexNumbersKeepsTextWithoutNumbers() {
        assertEquals("Привет, мир!",
                StringProcessor.replaceHexNumbers("Привет, мир!"));
    }

    @Test
    void replaceHexNumbersKeepsTooShortNumber() {
        assertEquals("0x123",
                StringProcessor.replaceHexNumbers("0x123"));
    }

    @Test
    void replaceHexNumbersKeepsTooLongNumber() {
        assertEquals("0x123456789",
                StringProcessor.replaceHexNumbers("0x123456789"));
    }

    @Test
    void replaceHexNumbersWorksWithEmptyString() {
        assertEquals("", StringProcessor.replaceHexNumbers(""));
    }
}