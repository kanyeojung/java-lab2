package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void constructorSavesAllFields() {
        Payment payment = new Payment("Иванов Иван", 12, 5, 2026, 12550);

        assertEquals("Иванов Иван", payment.getFullName());
        assertEquals(12, payment.getDay());
        assertEquals(5, payment.getMonth());
        assertEquals(2026, payment.getYear());
        assertEquals(12550, payment.getAmount());
    }

    @Test
    void emptyConstructorCreatesPayment() {
        Payment payment = new Payment();

        assertNotNull(payment);
        assertEquals(0, payment.getAmount());
    }

    @Test
    void settersChangeAllFields() {
        Payment payment = new Payment();

        payment.setFullName("Петрова Анна");
        payment.setDay(3);
        payment.setMonth(11);
        payment.setYear(2025);
        payment.setAmount(9901);

        assertEquals("Петрова Анна", payment.getFullName());
        assertEquals(3, payment.getDay());
        assertEquals(11, payment.getMonth());
        assertEquals(2025, payment.getYear());
        assertEquals(9901, payment.getAmount());
    }

    @Test
    void equalsReturnsTrueForSameObject() {
        Payment payment = new Payment("Иванов", 1, 2, 2026, 100);

        assertEquals(payment, payment);
    }

    @Test
    void equalsReturnsTrueForPaymentsWithSameFields() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 2, 2026, 100);

        assertEquals(first, second);
        assertEquals(second, first);
    }

    @Test
    void equalsDetectsDifferentName() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Петров", 1, 2, 2026, 100);

        assertNotEquals(first, second);
    }

    @Test
    void equalsDetectsDifferentDay() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 2, 2, 2026, 100);

        assertNotEquals(first, second);
    }

    @Test
    void equalsDetectsDifferentMonth() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 3, 2026, 100);

        assertNotEquals(first, second);
    }

    @Test
    void equalsDetectsDifferentYear() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 2, 2025, 100);

        assertNotEquals(first, second);
    }

    @Test
    void equalsDetectsDifferentAmount() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 2, 2026, 101);

        assertNotEquals(first, second);
    }

    @Test
    void equalsReturnsFalseForNull() {
        Payment payment = new Payment("Иванов", 1, 2, 2026, 100);

        assertNotEquals(null, payment);
    }

    @Test
    void equalsReturnsFalseForAnotherClass() {
        Payment payment = new Payment("Иванов", 1, 2, 2026, 100);

        assertNotEquals(payment, "Иванов");
    }

    @Test
    void equalsHandlesNullNames() {
        Payment first = new Payment(null, 1, 2, 2026, 100);
        Payment second = new Payment(null, 1, 2, 2026, 100);
        Payment third = new Payment("Иванов", 1, 2, 2026, 100);

        assertEquals(first, second);
        assertNotEquals(first, third);
    }

    @Test
    void equalPaymentsHaveSameHashCode() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 2, 2026, 100);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void changingFieldUpdatesEquality() {
        Payment first = new Payment("Иванов", 1, 2, 2026, 100);
        Payment second = new Payment("Иванов", 1, 2, 2026, 100);

        second.setAmount(200);

        assertNotEquals(first, second);
    }

    @Test
    void toStringContainsPaymentData() {
        Payment payment = new Payment("Иванов Иван", 12, 5, 2026, 12550);

        String text = payment.toString();

        assertTrue(text.contains("Иванов Иван"));
        assertTrue(text.contains("12"));
        assertTrue(text.contains("5"));
        assertTrue(text.contains("2026"));
        assertTrue(text.contains("12550"));
    }
}