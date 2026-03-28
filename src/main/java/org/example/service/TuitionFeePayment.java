package org.example.service;

public class TuitionFeePayment implements TFPay {
    private final double PRICE_PER_UNIT = 1000;
    private double balance;
    private double totalTuitionFee;

    @Override
    public double calculateTuitionFee(int units, double discountRate){
        totalTuitionFee = units * PRICE_PER_UNIT;

        if(discountRate != 0){
            totalTuitionFee = totalTuitionFee - (totalTuitionFee * discountRate);
        }
        return totalTuitionFee;
    }

    @Override
    public void makePayment(double amount){
        balance = totalTuitionFee - amount;
    }

    @Override
    public double getBalance(){
        return balance;
    }

    @Override
    public boolean isFullyPaid(){
        return balance == 0 ? true : false;
    }
}
