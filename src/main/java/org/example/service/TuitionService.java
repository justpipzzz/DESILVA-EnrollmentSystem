package org.example.service;

import org.example.model.TuitionFeePayment;
import org.example.model.Student;
import java.util.ArrayList;
import java.util.List;

public class TuitionService implements ITuitionService {
    private static final double RATE_PER_UNIT = 1500.00;
    private List<TuitionFeePayment> accounts = new ArrayList<>();

    @Override
    public TuitionFeePayment getOrCreateAccount(Student student) {
        for (TuitionFeePayment acc : accounts) {
            if (acc.getStudent().getPersonID() == student.getPersonID()) {
                return acc;
            }
        }
        TuitionFeePayment newAccount = new TuitionFeePayment(student);
        accounts.add(newAccount);
        return newAccount;
    }

    @Override
    public double calculateFee(TuitionFeePayment payment, int units) {
        double total = units * RATE_PER_UNIT;
        payment.setTotalTuitionFee(payment.getTotalTuitionFee() + total);
        return total;
    }

    @Override
    public void makePayment(TuitionFeePayment payment, double amount) {
        if (amount > 0) {
            double balance = getRemainingBalance(payment);
            double actualPayment = Math.min(amount, balance); // Prevents negative balance overpaying
            payment.setAmountPaid(payment.getAmountPaid() + actualPayment);
        }
    }

    @Override
    public double getRemainingBalance(TuitionFeePayment payment) {
        return payment.getTotalTuitionFee() - payment.getAmountPaid();
    }
}