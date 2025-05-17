package com.example;

import java.util.List;

public class Report {
    public static void generateSalesReport(List<Sale> sales) {
        for (Sale sale : sales) {
            System.out.println("Sale ID: " + sale.getId());
            System.out.println("Car: " + sale.getCar().getMake() + " " + sale.getCar().getModel());
            System.out.println("Customer: " + sale.getCustomer().getName());
            System.out.println("Sale Price: " + sale.getSalePrice());
            System.out.println("---");
        }
    }
}
