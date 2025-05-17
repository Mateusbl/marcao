package com.example;

public class Service {
    private String id;
    private String description;
    private double cost;

    public Service(String id, String description, double cost) {
        this.id = id;
        this.description = description;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
