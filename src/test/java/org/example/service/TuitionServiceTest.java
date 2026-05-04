package org.example.service;

import org.example.model.Student;
import org.example.model.TuitionFeePayment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TuitionServiceTest {

    private TuitionService tuitionService;
    private TuitionFeePayment payment;

    // @BeforeEach runs before EVERY test to give us a fresh, clean setup
    @BeforeEach
    void setUp() {
        tuitionService = new TuitionService();
        Student student = new Student(1, "Test Student", "BSIT");
        payment = new TuitionFeePayment(student);
    }

    @Test
    void testCalculateFee() {
        // Arrange & Act: Calculate fee for 10 units (10 * 1500 = 15000)
        double calculatedFee = tuitionService.calculateFee(payment, 10);

        // Assert: Prove the math is correct
        assertEquals(15000.0, calculatedFee, "Calculation should be exactly 15000.0");
        assertEquals(15000.0, payment.getTotalTuitionFee(), "Payment object total fee should be updated");
    }

    @Test
    void testMakePaymentAndGetBalance() {
        // Arrange: Assess 10 units first
        tuitionService.calculateFee(payment, 10); // Total is 15000

        // Act: Make a payment of 5000
        tuitionService.makePayment(payment, 5000);

        // Assert: Prove the payment and balance are recorded correctly
        assertEquals(5000.0, payment.getAmountPaid(), "Amount paid should be 5000");
        assertEquals(10000.0, tuitionService.getRemainingBalance(payment), "Remaining balance should be 10000");
    }
}