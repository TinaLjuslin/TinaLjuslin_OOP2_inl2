package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Tie, has length and width of a tie
 * @author Tina Ljuslin
 */
@JsonTypeName("Tie")
public class Tie extends Item {
    private double length;
    private double width;

    /**
     * Empty constructor
     */
    public Tie() {}
    /**
     * Constructor, creates a new tie
     * @param pattern pattern of tie
     * @param material, material of tie
     * @param brand, brand of tie
     * @param length, length of tie
     * @param width, width of tie
     */
    public Tie(Pattern pattern, Material material, String brand, String color, double pricePerDay,
               double length,
               double width) {
        super(pattern, material, brand, color, pricePerDay);
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Overrides toString()
     * @return string of tie
     */
    @Override
    public String toString() {
        String s = getPattern().toString();
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        s = s.concat(" ").concat(getColor());
        s = s.concat(" ").concat(getMaterial().toString());
        s = s.concat(" tie from ");
        s = s.concat(getBrand());
        s = s.concat(", length: ").concat(String.valueOf(length));
        s = s.concat(", width: ").concat(String.valueOf(width));
        s = s.concat(", rental price per day is ").concat(String.valueOf(getPricePerDay())).concat(
                " SEK");
        s = s.concat(", ID: ").concat(getItemID());
        return s;
    }
}
