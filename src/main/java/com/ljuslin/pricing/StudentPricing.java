package com.ljuslin.pricing;
/**
 * Pricing methods for level student
 * @author Tina Ljuslin
 */
public class StudentPricing implements PricePolicy{
private final int DISCOUNT_PERCENTAGE = 10;
    /**
     * Returns price per day for student level
     * @param price price of item
     * @return price per day for student
     */
    public double getPricePerDay(double price){
        return price * (100 - DISCOUNT_PERCENTAGE) / 100;
    }
    /**
     * Returns total price for level student
     * @param price price per day
     * @param days number of days
     * @return the total sum
     */
    public double getTotalPrice(double price, int days){
        return price * days * (100 - DISCOUNT_PERCENTAGE) / 100;
    }
}
