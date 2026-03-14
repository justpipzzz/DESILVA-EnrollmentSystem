package org.example.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TuitionFeePaymentTest {
    private TuitionFeePayment tuitionFeePayment;

    @BeforeEach
    void setUp() {
        tuitionFeePayment = new TuitionFeePayment();

    }

    @Test
    void shouldCalculateCorrectTuitionFeeWithNoDiscount() {
        //Assert
        assertEquals(5000, tuitionFeePayment.calculateTuitionFee(5,0));

    }

    @Test
    void shouldCalculateCorrectTuitionFeeWithDiscount() {
        //Assert
        assertEquals(4500, tuitionFeePayment.calculateTuitionFee(5,0.10));
    }

    @Test
    void shouldMakePaymentOf500() {
        tuitionFeePayment.calculateTuitionFee(5,0);
        tuitionFeePayment.makePayment(500);

        assertEquals(4500, tuitionFeePayment.getBalance());

    }

    @Test
    void shouldBeFullyPaid() {
        tuitionFeePayment.calculateTuitionFee(5,0);
        tuitionFeePayment.makePayment(5000);

        assertTrue(tuitionFeePayment.isFullyPaid());
    }

    @Test
    void shouldBeNotFullyPaid() {
        tuitionFeePayment.calculateTuitionFee(5,0);
        tuitionFeePayment.makePayment(6000);

        assertFalse(tuitionFeePayment.isFullyPaid());
    }
}