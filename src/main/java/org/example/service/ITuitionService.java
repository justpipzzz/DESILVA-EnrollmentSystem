package org.example.service;

import org.example.model.TuitionFeePayment;

public interface ITuitionService {
    double calculateFee(TuitionFeePayment payment, int units);
    void makePayment(TuitionFeePayment payment, double amount);
    double getRemainingBalance(TuitionFeePayment payment);
}