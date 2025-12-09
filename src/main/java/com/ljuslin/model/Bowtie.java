package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Represents a bowtie in the shop, holds size and boolean pre-tied
 *
 * @author Tina Ljuslin
 */
@JsonTypeName("Bowtie")
public class Bowtie extends Item {
    private String size;
    private boolean preTied = false;

    public Bowtie() {
    }

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

    public String getSize() {
        return size;
    }

    public boolean isPreTied() {
        return preTied;
    }

    @Override
    public String toString() {
        String s = getPattern().toString();

        s = s.concat(" ").concat(getColor());
        s = s.concat(" fluga i ").concat(getMaterial().toString());
        s = s.concat(" från ");
        s = s.concat(getBrand());
        if (preTied)
            s = s.concat(", förknuten");
        else
            s = s.concat(", oknuten");
        s = s.concat(", i storlek ").concat(size);
        s = s.concat(", pris per dag ").concat(String.valueOf(getPricePerDay())).concat(
                " kr");
        s = s.concat(", ID: ").concat(String.valueOf(getItemID()));
        return s;
    }
}
