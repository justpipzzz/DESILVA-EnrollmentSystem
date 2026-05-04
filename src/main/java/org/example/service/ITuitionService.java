package org.example.service;

import org.example.model.TuitionFeePayment;
import org.example.model.Student;

public interface ITuitionService {
    TuitionFeePayment getOrCreateAccount(Student student);
    double calculateFee(TuitionFeePayment payment, int units);
    void makePayment(TuitionFeePayment payment, double amount);
    double getRemainingBalance(TuitionFeePayment payment);
}