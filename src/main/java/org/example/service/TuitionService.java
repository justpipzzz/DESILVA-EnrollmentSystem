package org.example.service;

import org.example.model.TuitionFeePayment;
import org.example.model.Student;
import org.example.model.Course;
import java.util.ArrayList;
import java.util.List;

public class TuitionService implements ITuitionService {
    private static final double RATE_PER_UNIT = 1500.00; // Php 1500 per unit
    private List<TuitionFeePayment> accounts = new ArrayList<>();

    @Override
    public TuitionFeePayment getOrCreateAccount(Student student) {
        for (TuitionFeePayment acc : accounts) {
            if (acc.getStudent().getPersonID() == student.getPersonID()) return acc;
        }
        TuitionFeePayment newAccount = new TuitionFeePayment(student);
        accounts.add(newAccount);
        return newAccount;
    }

    @Override
    public double calculateFee(TuitionFeePayment payment) {
        int totalUnits = 0;
        // Automatically sums up units from the student's enrolled courses!
        for (Course c : payment.getStudent().getEnrolledCourses()) {
            totalUnits += c.getUnits();
        }
        double total = totalUnits * RATE_PER_UNIT;
        payment.setTotalTuitionFee(total);
        return total;
    }

    @Override
    public void makePayment(TuitionFeePayment payment, double amount) {
        if (amount > 0) {
            double balance = getRemainingBalance(payment);
            double actualPayment = Math.min(amount, balance);
            payment.setAmountPaid(payment.getAmountPaid() + actualPayment);
        }
    }

    @Override
    public double getRemainingBalance(TuitionFeePayment payment) {
        return payment.getTotalTuitionFee() - payment.getAmountPaid();
    }
}