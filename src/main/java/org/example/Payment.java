package org.example;
import java.util.Objects;

public class Payment {
    private String fullName;
    private int day;
    private int month;
    private int year;
    private int amount;

    public Payment(){}

    public Payment(String fullName, int day, int month, int year, int amount) {
        this.fullName = fullName;
        this.day = day;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Payment other = (Payment) obj;
        return day == other.day && month == other.month && year == other.year && amount == other.amount && Objects.equals(fullName, other.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year, amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "fullName='" + fullName + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", amount=" + amount +
                '}';
    }
}