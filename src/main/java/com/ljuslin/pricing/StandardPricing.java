package com.ljuslin.pricing;
/**
 * Pricing methods for level standard
 * @author Tina Ljuslin
 */
public class StandardPricing implements PricePolicy {

    /**
     * Returns price per day for standard level
     * @param price
     * @return
     */
    public double getPricePerDay(double price){
        return price;
    }

    /**
     * Returns total price for standard pricing
     * @param price price per day
     * @param days number of days
     * @return the total sum
     */
    public double getTotalPrice(double price, int days){
        return price * days;
    }

}
