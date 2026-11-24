package org.example;

public class FinanceReportProcessor {

    public static FinanceReport bySurnameFirstLetter(FinanceReport report, char letter) {
        int count = 0;

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);

            if (payment!= null && payment.getFullName() != null && !payment.getFullName().isEmpty() && payment.getFullName().charAt(0) == letter) {
                count++;
            }
        }

        Payment[] selected = new Payment[count];
        int next = 0;

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);

            if (payment!= null && payment.getFullName() != null && !payment.getFullName().isEmpty() && payment.getFullName().charAt(0) == letter) {
                selected[next] = payment;
                next++;
            }
        }

        return new FinanceReport(selected, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }

    public static FinanceReport withAmountLessThan(FinanceReport report, int limit) {
        int count = 0;

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);

            if (payment!= null && payment.getAmount() < limit) {
                count++;
            }
        }
        Payment[] selected = new Payment[count];
        int next = 0;

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);

            if (payment!= null && payment.getAmount() < limit) {
                selected[next] = payment;
                next++;
            }
        }

        return new FinanceReport(selected, report.getAuthor(), report.getDay(), report.getMonth(), report.getYear());
    }

    public static long totalAmountOnDate(FinanceReport report, String date) {
        String[] parts = date.split("\\.");

        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = 2000 + Integer.parseInt(parts[2]);

        long total = 0;

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);
            if (payment!= null && payment.getDay() == day && payment.getMonth() == month && payment.getYear() == year) {
                total += payment.getAmount();
            }
        }

        return total;
    }

    public static String[] monthsWithoutPayments(FinanceReport report, int year) {
        String[] monthsNames = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

        boolean[] hasPayments = new boolean[12];

        for (int i = 0; i < report.getPaymentCount(); i++){
            Payment payment = report.getPayment(i);

            if (payment!= null && payment.getYear() == year &&  payment.getMonth() >= 1 &&  payment.getMonth() <= 12) {
                hasPayments[payment.getMonth() - 1] = true;
            }
        }

        int emptyCount = 0;
        for (int i = 0; i < 12; i++){
            if (!hasPayments[i]){
                emptyCount++;
            }
        }

        String[] result = new String[emptyCount];
        int next = 0;

        for (int i = 0; i < 12; i++){
            if (!hasPayments[i]){
                result[next] = monthsNames[i];
                next++;
            }
        }

        return result;
    }
}
