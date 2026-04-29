package org.example.service;

import org.example.model.TuitionFeePayment;

public class TuitionService implements ITuitionService {
    // Let's assume a standard rate per unit for this calculation
    private static final double RATE_PER_UNIT = 1500.00;

    @Override
    public double calculateFee(TuitionFeePayment payment, int units) {
        double total = units * RATE_PER_UNIT;
        payment.setTotalTuitionFee(total);
        return total;
    }

    @Override
    public void makePayment(TuitionFeePayment payment, double amount) {
        if (amount > 0) {
            double currentPaid = payment.getAmountPaid();
            payment.setAmountPaid(currentPaid + amount);
        }
    }

    @Override
    public double getRemainingBalance(TuitionFeePayment payment) {
        return payment.getTotalTuitionFee() - payment.getAmountPaid();
    }
}