package org.example.model;

public class TuitionFeePayment {
    private Student student;
    private double totalTuitionFee;
    private double amountPaid;

    public TuitionFeePayment(Student student) {
        this.student = student;
        this.totalTuitionFee = 0.0;
        this.amountPaid = 0.0;
    }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public double getTotalTuitionFee() { return totalTuitionFee; }
    public void setTotalTuitionFee(double totalTuitionFee) { this.totalTuitionFee = totalTuitionFee; }

    public double getAmountPaid() { return amountPaid; }
    public void setAmountPaid(double amountPaid) { this.amountPaid = amountPaid; }
}