package org.example;

public class FinanceReport {
    private Payment[] payments;
    private String author;
    private int day;
    private int month;
    private int year;

    public FinanceReport(Payment[] payments, String author, int day, int month, int year) {
        this.payments = payments;
        this.author = author;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getPaymentCount() {
        return payments.length;
    }

    public Payment getPayment(int index) {
        return payments[index];
    }

    public void setPayment(int index, Payment payment) {
        payments[index] = payment;
    }

    public String getAuthor() {
        return author;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format( "[Автор: %s, дата: %02d.%02d.%d, Платежи: [\n", author, day, month, year));

        for (Payment payment : payments) {
            int rubles = payment.getAmount() / 100;
            int kopecks = payment.getAmount() % 100;

        result.append(String.format("Плательщик: %s, дата: %02d.%02d.%d сумма: %d руб. %02d коп.\n",
                payment.getFullName(), payment.getDay(), payment.getMonth(), payment.getYear(), rubles, kopecks));
        }

        result.append("]]");
        return result.toString();
    }
}