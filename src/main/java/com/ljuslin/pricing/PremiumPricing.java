package com.ljuslin.pricing;
/**
 * Pricing methods for level premium
 */

public class PremiumPricing implements PricePolicy{
    private final int DISCOUNT_PERCENTAGE = 20;
    /**
     * Returns prica per day for level premium
     * @param price price
     * @return price per day for level premium
     */
    public double getPricePerDay(double price){
        return price * (100 - DISCOUNT_PERCENTAGE) / 100.0;
    }

    /**
     * Calculates total price for level premium
     * @param price price
     * @param days number of days
     * @return total sum for level premium
     */
    public double getTotalPrice(double price, int days){
        return price * (100 - DISCOUNT_PERCENTAGE) / 100 * days;
    }

}
