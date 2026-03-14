package org.example.service;

public class TuitionFeePayment {
    private final double PRICE_PER_UNIT = 1000;
    private double balance;
    private double totalTuitionFee;

    public double calculateTuitionFee(int units, double discountRate){
        totalTuitionFee = units * PRICE_PER_UNIT;

        if(discountRate != 0){
            totalTuitionFee = totalTuitionFee - (totalTuitionFee * discountRate);
        }
        return totalTuitionFee;
    }

    public void makePayment(double amount){
        balance = totalTuitionFee - amount;
    }

    public double getBalance(){
        return balance;
    }

    public boolean isFullyPaid(){
        return balance == 0 ? true : false;
    }
}
