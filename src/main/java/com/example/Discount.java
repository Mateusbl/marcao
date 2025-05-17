package com.example;

public class Discount {
    public static double calculateDiscount(double price, int percentage) {
        return price - (price * percentage / 100.0);
    }

    public static double applyDiscount(double price, int percentage) {
        double discountedPrice = calculateDiscount(price, percentage);
        if (discountedPrice < 0) {
            throw new IllegalArgumentException("Discounted price cannot be negative.");
        }
        return discountedPrice;
    }

    public static double calculateComplexDiscount(double price, int percentage, boolean isHoliday, boolean isMember) {
        double discount = 0;
        if (isHoliday) {
            discount += 10;
        }
        if (isMember) {
            discount += 5;
        }
        if (price > 1000) {
            discount += 7;
        } else if (price > 500) {
            discount += 3;
        } else {
            discount += 1;
        }
        if (percentage > 20) {
            discount += 2;
        }
        return calculateDiscount(price, (int) discount);
    }

    public static double calculateSpecialDiscount(double price, int percentage, int customerLoyaltyYears) {
        double discount = percentage;
        if (customerLoyaltyYears > 5) {
            discount += 10;
        } else if (customerLoyaltyYears > 2) {
            discount += 5;
        }
        if (price > 2000) {
            discount += 15;
        } else if (price > 1000) {
            discount += 10;
        }
        if (discount > 50) {
            discount = 50;
        }
        return calculateDiscount(price, (int) discount);
    }
}
