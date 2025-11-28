package com.ljuslin.pricing;
/**
 * Interface for prices
 */
public interface PricePolicy {
    public double getPricePerDay(double price);
    public double getTotalPrice(double price, int days);

}

