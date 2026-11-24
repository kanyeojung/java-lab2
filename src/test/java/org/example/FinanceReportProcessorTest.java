package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceReportProcessorTest {

    @Test
    void selectsPaymentsBySurnameFirstLetter() {
        Payment first = new Payment("Иванов Иван", 1, 2, 2026, 10000);
        Payment second = new Payment("Петров Пётр", 2, 2, 2026, 20000);
        Payment third = new Payment("Ильина Анна", 3, 2, 2026, 30000);

        FinanceReport report = new FinanceReport(
                new Payment[]{first, second, third}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.bySurnameFirstLetter(report, 'И');

        assertEquals(2, result.getPaymentCount());
        assertEquals(first, result.getPayment(0));
        assertEquals(third, result.getPayment(1));
    }

    @Test
    void returnsEmptyReportWhenNoSurnameMatches() {
        Payment payment = new Payment("Петров Пётр", 1, 2, 2026, 10000);
        FinanceReport report = new FinanceReport(
                new Payment[]{payment}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.bySurnameFirstLetter(report, 'И');

        assertEquals(0, result.getPaymentCount());
    }

    @Test
    void surnameSelectionSkipsEmptyNamesAndNullPayments() {
        Payment emptyName = new Payment("", 1, 2, 2026, 10000);
        Payment matching = new Payment("Иванов Иван", 2, 2, 2026, 20000);
        FinanceReport report = new FinanceReport(
                new Payment[]{null, emptyName, matching}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.bySurnameFirstLetter(report, 'И');

        assertEquals(1, result.getPaymentCount());
        assertEquals(matching, result.getPayment(0));
    }

    @Test
    void selectsPaymentsStrictlyBelowLimit() {
        Payment cheaper = new Payment("Иванов Иван", 1, 2, 2026, 999);
        Payment equal = new Payment("Петров Пётр", 2, 2, 2026, 1000);
        Payment dearer = new Payment("Сидоров Семён", 3, 2, 2026, 1001);
        FinanceReport report = new FinanceReport(
                new Payment[]{cheaper, equal, dearer}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.withAmountLessThan(report, 1000);

        assertEquals(1, result.getPaymentCount());
        assertEquals(cheaper, result.getPayment(0));
    }

    @Test
    void returnsEmptyReportWhenNoAmountIsBelowLimit() {
        Payment payment = new Payment("Иванов Иван", 1, 2, 2026, 1000);
        FinanceReport report = new FinanceReport(
                new Payment[]{payment}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.withAmountLessThan(report, 1000);

        assertEquals(0, result.getPaymentCount());
    }

    @Test
    void amountSelectionSkipsNullPayments() {
        Payment matching = new Payment("Иванов Иван", 1, 2, 2026, 500);
        FinanceReport report = new FinanceReport(
                new Payment[]{null, matching}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.withAmountLessThan(report, 1000);

        assertEquals(1, result.getPaymentCount());
        assertEquals(matching, result.getPayment(0));
    }

    @Test
    void selectedReportKeepsAuthorAndDate() {
        Payment payment = new Payment("Иванов Иван", 1, 2, 2026, 500);
        FinanceReport report = new FinanceReport(
                new Payment[]{payment}, "Полина", 20, 11, 2026
        );

        FinanceReport result = FinanceReportProcessor.withAmountLessThan(report, 1000);

        assertEquals("Полина", result.getAuthor());
        assertEquals(20, result.getDay());
        assertEquals(11, result.getMonth());
        assertEquals(2026, result.getYear());
    }

    @Test
    void totalAmountOnDateAddsPaymentsFromSeveralPeople() {
        Payment first = new Payment("Иванов Иван", 5, 11, 2026, 500);
        Payment second = new Payment("Петров Пётр", 5, 11, 2026, 750);
        Payment anotherDay = new Payment("Сидоров Семён", 6, 11, 2026, 1000);

        FinanceReport report = new FinanceReport(
                new Payment[]{first, second, anotherDay}, "Полина", 20, 11, 2026
        );

        assertEquals(1250L,
                FinanceReportProcessor.totalAmountOnDate(report, "05.11.26"));
    }

    @Test
    void totalAmountOnDateChecksMonthAndYearToo() {
        FinanceReport report = new FinanceReport(
                new Payment[]{
                        new Payment("Иванов Иван", 5, 11, 2026, 500),
                        new Payment("Петров Пётр", 5, 12, 2026, 750),
                        new Payment("Сидоров Семён", 5, 11, 2025, 1000)
                },
                "Полина", 20, 11, 2026
        );

        assertEquals(500L,
                FinanceReportProcessor.totalAmountOnDate(report, "05.11.26"));
    }

    @Test
    void totalAmountOnDateReturnsZeroWhenThereAreNoMatches() {
        FinanceReport report = new FinanceReport(
                new Payment[]{new Payment("Иванов Иван", 5, 11, 2026, 500)},
                "Полина", 20, 11, 2026
        );

        assertEquals(0L,
                FinanceReportProcessor.totalAmountOnDate(report, "01.01.26"));
    }

    @Test
    void totalAmountOnDateSkipsEmptyArrayCells() {
        FinanceReport report = new FinanceReport(
                new Payment[]{null, new Payment("Иванов Иван", 5, 11, 2026, 500)},
                "Полина", 20, 11, 2026
        );

        assertEquals(500L,
                FinanceReportProcessor.totalAmountOnDate(report, "05.11.26"));
    }

    @Test
    void monthsWithoutPaymentsReturnsMissingMonthsInOrder() {
        Payment january = new Payment("Иванов Иван", 5, 1, 2026, 500);
        Payment march = new Payment("Петров Пётр", 8, 3, 2026, 750);
        FinanceReport report = new FinanceReport(
                new Payment[]{january, march}, "Полина", 20, 11, 2026
        );

        String[] result = FinanceReportProcessor.monthsWithoutPayments(report, 2026);

        assertArrayEquals(new String[]{
                "февраль", "апрель", "май", "июнь", "июль",
                "август", "сентябрь", "октябрь", "ноябрь", "декабрь"
        }, result);
    }

    @Test
    void monthsWithoutPaymentsReturnsAllMonthsForYearWithoutPayments() {
        FinanceReport report = new FinanceReport(
                new Payment[]{new Payment("Иванов Иван", 5, 1, 2025, 500)},
                "Полина", 20, 11, 2026
        );

        String[] result = FinanceReportProcessor.monthsWithoutPayments(report, 2026);

        assertArrayEquals(new String[]{
                "январь", "февраль", "март", "апрель",
                "май", "июнь", "июль", "август",
                "сентябрь", "октябрь", "ноябрь", "декабрь"
        }, result);
    }

    @Test
    void monthsWithoutPaymentsReturnsEmptyArrayWhenEveryMonthHasPayment() {
        Payment[] payments = new Payment[12];

        for (int month = 1; month <= 12; month++) {
            payments[month - 1] = new Payment(
                    "Иванов Иван", 1, month, 2026, 100
            );
        }

        FinanceReport report = new FinanceReport(
                payments, "Полина", 20, 11, 2026
        );

        String[] result = FinanceReportProcessor.monthsWithoutPayments(report, 2026);

        assertArrayEquals(new String[0], result);
    }

    @Test
    void monthsWithoutPaymentsIgnoresNullAndInvalidMonth() {
        FinanceReport report = new FinanceReport(
                new Payment[]{
                        null,
                        new Payment("Иванов Иван", 1, 0, 2026, 100),
                        new Payment("Петров Пётр", 1, 13, 2026, 200),
                        new Payment("Сидоров Семён", 1, 12, 2026, 300)
                },
                "Полина", 20, 11, 2026
        );

        String[] result = FinanceReportProcessor.monthsWithoutPayments(report, 2026);

        assertArrayEquals(new String[]{
                "январь", "февраль", "март", "апрель",
                "май", "июнь", "июль", "август",
                "сентябрь", "октябрь", "ноябрь"
        }, result);
    }

    @Test
    void totalAmountOnDateHandlesSumLargerThanInt() {
        FinanceReport report = new FinanceReport(
                new Payment[]{
                        new Payment("Иванов", 5, 11, 2026, 2_000_000_000),
                        new Payment("Петров", 5, 11, 2026, 2_000_000_000)
                },
                "Полина", 20, 11, 2026
        );

        assertEquals(4_000_000_000L,
                FinanceReportProcessor.totalAmountOnDate(report, "05.11.26"));
    }
}