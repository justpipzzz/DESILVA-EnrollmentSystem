package org.example.service;

import org.example.model.TuitionFeePayment;
import org.example.model.Student;

public interface ITuitionService {
    TuitionFeePayment getOrCreateAccount(Student student);
    double calculateFee(TuitionFeePayment payment); // No longer needs 'units' inputted!
    void makePayment(TuitionFeePayment payment, double amount);
    double getRemainingBalance(TuitionFeePayment payment);
}