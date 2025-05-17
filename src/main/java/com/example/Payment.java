package com.example;

public class Payment {
    private String id;
    private String saleId;
    private double amount;
    private String paymentMethod;

    public Payment(String id, String saleId, double amount, String paymentMethod) {
        this.id = id;
        this.saleId = saleId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public String getId() {
        return id;
    }

    public String getSaleId() {
        return saleId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
