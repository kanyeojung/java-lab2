package org.example;

//TIP Чтобы <b>запустить</b> код, нажмите <shortcut actionId="Run"/> или
// нажмите на значок <icon src="AllIcons.Actions.Execute"/> на полях.
public class Main {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder("abcde");
        StringProcessor.removeEverySecond(text);
        System.out.println(text);
        System.out.println(StringProcessor.replaceHexNumbers(
                "Васе 0x00000010 лет, а число 0x000000FF равно 255"
        ));
    }
}
