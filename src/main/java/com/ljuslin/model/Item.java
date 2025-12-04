package com.ljuslin.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * fick inte subklasser korrekt i min json, detta löste problemet, det räckte inte med
 * @JsonTypeName("Tie") i subklassen
 */
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
    private String itemID;
    private Pattern pattern;
    private Material material;
    private String brand;
    private String color;
    private double pricePerDay;
    private boolean available;

    public Item() {}

    public Item(Pattern pattern, Material material, String brand, String color,
                double pricePerDay) {
        this.itemID = String.valueOf(System.currentTimeMillis());
        this.pattern = pattern;
        this.material = material;
        this.brand = brand;
        this.color = color;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Material getMaterial() {
        return material;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getItemID() {
        return itemID;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setItemID(String itemID) {
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
