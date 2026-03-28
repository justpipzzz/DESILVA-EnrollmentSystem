package org.example.service;

public interface TFPay {
    double calculateTuitionFee(int units, double tuitionFee);
    void makePayment(double amount);
    double getBalance();
    boolean isFullyPaid();
}
