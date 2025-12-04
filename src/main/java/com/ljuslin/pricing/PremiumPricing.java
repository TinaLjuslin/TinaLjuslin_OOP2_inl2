package com.ljuslin.pricing;
/**
 * Pricing methods for level premium
 */

public class PremiumPricing implements PricePolicy{
    private final int DISCOUNT_PERCENTAGE = 20;

    public double getPricePerDay(double price){
        return price * (100 - DISCOUNT_PERCENTAGE) / 100.0;
    }

    public double getTotalPrice(double price, int days){
        return price * (100 - DISCOUNT_PERCENTAGE) / 100 * days;
    }

}
