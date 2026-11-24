package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceReportTest {

    @Test
    void constructorSavesAuthorAndDate() {
        FinanceReport report = new FinanceReport(
                new Payment[0], "Лазарева Полина", 4, 5, 2026
        );

        assertEquals("Лазарева Полина", report.getAuthor());
        assertEquals(4, report.getDay());
        assertEquals(5, report.getMonth());
        assertEquals(2026, report.getYear());
    }

    @Test
    void getPaymentCountReturnsArrayLength() {
        Payment[] payments = {
                new Payment("Иванов", 1, 5, 2026, 100),
                new Payment("Петров", 2, 5, 2026, 200)
        };
        FinanceReport report =
                new FinanceReport(payments, "Автор", 3, 5, 2026);

        assertEquals(2, report.getPaymentCount());
    }

    @Test
    void emptyReportHasZeroPayments() {
        FinanceReport report =
                new FinanceReport(new Payment[0], "Автор", 3, 5, 2026);

        assertEquals(0, report.getPaymentCount());
    }

    @Test
    void getPaymentReturnsRequestedPayment() {
        Payment first = new Payment("Иванов", 1, 5, 2026, 100);
        Payment second = new Payment("Петров", 2, 5, 2026, 200);
        FinanceReport report = new FinanceReport(
                new Payment[]{first, second}, "Автор", 3, 5, 2026
        );

        assertSame(second, report.getPayment(1));
    }

    @Test
    void setPaymentReplacesRequestedPayment() {
        Payment first = new Payment("Иванов", 1, 5, 2026, 100);
        Payment replacement = new Payment("Сидоров", 4, 5, 2026, 300);
        FinanceReport report = new FinanceReport(
                new Payment[]{first}, "Автор", 3, 5, 2026
        );

        report.setPayment(0, replacement);

        assertSame(replacement, report.getPayment(0));
    }

    @Test
    void toStringShowsPaymentAndFormatsKopecks() {
        Payment payment = new Payment("Иванов Иван", 3, 5, 2026, 12505);
        FinanceReport report = new FinanceReport(
                new Payment[]{payment}, "Лазарева Полина", 4, 5, 2026
        );

        String text = report.toString();

        assertTrue(text.contains("Автор: Лазарева Полина"));
        assertTrue(text.contains("дата: 04.05.2026"));
        assertTrue(text.contains("Плательщик: Иванов Иван"));
        assertTrue(text.contains("дата: 03.05.2026"));
        assertTrue(text.contains("125 руб. 05 коп."));
    }

    @Test
    void toStringWorksForEmptyReport() {
        FinanceReport report = new FinanceReport(
                new Payment[0], "Автор", 4, 5, 2026
        );

        String text = report.toString();

        assertTrue(text.contains("Автор: Автор"));
        assertTrue(text.contains("Платежи: ["));
        assertTrue(text.endsWith("]]"));
    }

    @Test
    void copyConstructorCopiesReportData() {
        Payment payment = new Payment("Иванов Иван", 12, 5, 2026, 12550);
        FinanceReport original = new FinanceReport(
                new Payment[]{payment}, "Полина", 20, 11, 2026
        );

        FinanceReport copy = new FinanceReport(original);

        assertEquals("Полина", copy.getAuthor());
        assertEquals(20, copy.getDay());
        assertEquals(11, copy.getMonth());
        assertEquals(2026, copy.getYear());
        assertEquals(1, copy.getPaymentCount());
        assertEquals(payment, copy.getPayment(0));
    }

    @Test
    void changingOriginalPaymentDoesNotChangeCopy() {
        Payment payment = new Payment("Иванов Иван", 12, 5, 2026, 12550);
        FinanceReport original = new FinanceReport(
                new Payment[]{payment}, "Полина", 20, 11, 2026
        );

        FinanceReport copy = new FinanceReport(original);
        payment.setAmount(99999);
        payment.setFullName("Другой человек");

        assertEquals(12550, copy.getPayment(0).getAmount());
        assertEquals("Иванов Иван", copy.getPayment(0).getFullName());
        assertNotSame(payment, copy.getPayment(0));
    }

    @Test
    void replacingPaymentInOriginalDoesNotChangeCopy() {
        Payment first = new Payment("Иванов Иван", 12, 5, 2026, 12550);
        FinanceReport original = new FinanceReport(
                new Payment[]{first}, "Полина", 20, 11, 2026
        );

        FinanceReport copy = new FinanceReport(original);
        original.setPayment(
                0, new Payment("Петров Пётр", 1, 6, 2026, 500)
        );

        assertEquals("Иванов Иван", copy.getPayment(0).getFullName());
        assertEquals(12550, copy.getPayment(0).getAmount());
    }
}