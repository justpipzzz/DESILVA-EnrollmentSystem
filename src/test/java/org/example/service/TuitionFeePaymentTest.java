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
    @DisplayName("Calculate Tuition Fee")
    void shouldCalculateCorrectTuitionFeeWithNoDiscount() {
        //Assert
        assertEquals(5000, tuitionFeePayment.calculateTuitionFee(5,0));

    }

    @Test
    @DisplayName("Calculate Tuition Fee w/ Discount")
    void shouldCalculateCorrectTuitionFeeWithDiscount() {
        //Assert
        assertEquals(4500, tuitionFeePayment.calculateTuitionFee(5,0.10));
    }

    @Test
    @DisplayName("Should Make Payment of 6000")
    void shouldMakePaymentOf6000() {

        //Assert

    }
}