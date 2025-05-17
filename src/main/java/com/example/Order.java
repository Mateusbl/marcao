package com.example;

import java.util.Date;

public class Order {
    private String id;
    private Car car;
    private Customer customer;
    private Date orderDate;

    public Order(String id, Car car, Customer customer, Date orderDate) {
        this.id = id;
        this.car = car;
        this.customer = customer;
        this.orderDate = orderDate;
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

    public Date getOrderDate() {
        return orderDate;
    }
}
