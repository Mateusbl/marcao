package com.example;

import java.util.Date;

public class Sale {
    private String id;
    private Car car;
    private Customer customer;
    private Date saleDate;
    private double salePrice;

    public Sale(String id, Car car, Customer customer, Date saleDate, double salePrice) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.saleDate = saleDate;
        this.salePrice = salePrice;
    }

    public String getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public double getSalePrice() {
        return salePrice;
    }
}
