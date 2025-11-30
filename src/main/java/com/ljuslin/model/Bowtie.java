package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a bowtie in the shop, holds size and boolean pre-tied
 * @author Tina Ljuslin
 */
@JsonTypeName("Bowtie")
public class Bowtie extends Item {
    private String size;
    private boolean preTied = false;

    public Bowtie() {}

    public Bowtie(Pattern pattern, Material material, String brand, String color,
                  double pricePerDay,
                  String size, boolean preeTied) {
        super(pattern, material, brand, color, pricePerDay);
        this.size = size;
        this.preTied = preeTied;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setPreTied(boolean preTied) {
        this.preTied = preTied;
    }

    /**
     * Returns size of bowtie
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Returns if the bowtie is pree-tied
     * @return true if bowtie is pree-tied
     */
    public boolean isPreTied() {
        return preTied;
    }

    /**
     * Overides toString()
     * @return string representing bowtie
     */
    @Override
    public String toString() {
        String s = getPattern().toString();
        s = s.substring(0, 1).toUpperCase() + s.substring(1);
        s = s.concat(" ").concat(getColor());
        s = s.concat(" ").concat(getMaterial().toString());
        s = s.concat(" bowtie from ");
        s = s.concat(getBrand());
        if (preTied)
            s = s.concat(", pre-tied");
        else
            s = s.concat(", not pre-tied");
        s = s.concat(", in size ").concat(size);
        s = s.concat(", rental price per day is ").concat(String.valueOf(getPricePerDay())).concat(
                " SEK");
        s = s.concat(", ID: ").concat(getItemID());
        return s;
    }
}
