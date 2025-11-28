package com.ljuslin.model;
/**
 * Represents a bowtie in the shop, holds size and boolean pre-tied
 * @author Tina Ljuslin
 */
public class Bowtie extends Item {
    private String size;
    private boolean preeTied = false;

    /**
     * Empty constructor
     */
    public Bowtie() {}

    /**
     * Constructor, creates a new bowtie
     * @param pattern pattern of bowtie
     * @param material material of bowtie
     * @param brand brand of bowtie
     * @param pricePerDay price per day
     * @param size size of bowtie
     * @param preeTied true if bowtie is pre-tied
     */
    public Bowtie(Pattern pattern, Material material, String brand, String color,
                  double pricePerDay,
                  String size, boolean preeTied) {
        super(pattern, material, brand, color, pricePerDay);
        this.size = size;
        this.preeTied = preeTied;
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
    public boolean isPreeTied() {
        return preeTied;
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
        if (preeTied)
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
