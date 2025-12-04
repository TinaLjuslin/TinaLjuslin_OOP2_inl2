package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a tie in the shop, holds length and width
 *
 * @author Tina Ljuslin
 */
@JsonTypeName("Tie")
public class Tie extends Item {
    private double length;
    private double width;

    public Tie() {
    }

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

    @Override
    public String toString() {
        String s = getPattern().toString();
        s = s.concat(" ").concat(getColor());
        s = s.concat(" slips i ");
        s = s.concat(getMaterial().toString());
        s = s.concat(" från ");
        s = s.concat(getBrand());
        s = s.concat(", längd: ").concat(String.valueOf(length));
        s = s.concat("cm, bredd: ").concat(String.valueOf(width));
        s = s.concat("cm, pris per dag ").concat(String.valueOf(getPricePerDay())).concat(
                " kr");
        s = s.concat(", ID: ").concat(String.valueOf(getItemID()));


        return s;
    }
}
