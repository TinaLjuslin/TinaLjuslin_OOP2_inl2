package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        // Använd klassnamnet som typidentifikator
        use = JsonTypeInfo.Id.NAME,
        // Lägg till typinformationen som ett fält i JSON-objektet
        include = JsonTypeInfo.As.PROPERTY,
        // Namnet på fältet som ska innehålla typinformationen (t.ex. "Tie" eller "Bowtie")
        property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Tie.class, name = "Tie"),
        @JsonSubTypes.Type(value = Bowtie.class, name = "Bowtie")
})/**
 * Item in the store, abstract klass, holds item id, pattern, material, brand, color and price per
 * day
 * @author Tina Ljuslin
 */
public abstract class Item {
    private long itemID;
    private Pattern pattern;
    private Material material;
    private String brand;
    private String color;
    private double pricePerDay;
    private boolean available;

    public Item() {}

    public Item(Pattern pattern, Material material, String brand, String color,
                double pricePerDay) {
        this.itemID = System.currentTimeMillis();
        this.pattern = pattern;
        this.material = material;
        this.brand = brand;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    /**
     * Returns pattern of item
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    /**
     * Returns material of item
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Returns brand of item
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns color of item
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns rental price per day
     * @return price per day
     */
    public double getPricePerDay() {
        return pricePerDay;
    }

    /**
     * Returns items id
     * @return id of item
     */
    public long getItemID() {
        return itemID;
    }

    /**
     * Sets rental price per day
     * @param pricePerDay, new price
     */
    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
