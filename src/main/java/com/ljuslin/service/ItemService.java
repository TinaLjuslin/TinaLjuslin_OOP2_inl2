package com.ljuslin.service;

import com.ljuslin.model.Bowtie;
import com.ljuslin.model.Item;
import com.ljuslin.model.Tie;
import com.ljuslin.repository.Inventory;
import com.ljuslin.model.Material;
import com.ljuslin.model.Pattern;

import java.util.ArrayList;
import java.util.List;
/**
 * Handles items
 * @author Tina Ljuslin
 */
public class ItemService {
    private Inventory inventory;

    /**
     * Constructor, saves inventory
     * @param inventory the inventory to save
     */
    public ItemService(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Returns the item of an item id
     * @param itemID the id
     * @return the item found for id
     */
    public Item getItem(String itemID) {
        for (Item item : inventory.getItems()) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns all items containing the string from user
     * @param search the string to search for
     * @return list of found items
     */
    public List<Item> searchItem(String search) {
        List<Item> items = inventory.getItems();
        if (items == null || items.isEmpty()) {
            return null;
        }
        List<Item> searchtems = new ArrayList<>();
        for (Item item : items) {
            if (item.toString().toLowerCase().contains(search.toLowerCase())) {
                searchtems.add(item);
            }
        }
        return searchtems;
    }

    /**
     * Returns all items
     * @return
     */
    public List<Item> getItems() {
        return inventory.getItems();
    }

    /**
     * Returns all items
     * @return
     */
    public List<Item> getAvailableItems() {
        List<Item> availableItems = new ArrayList<>();
        for (Item item : inventory.getItems()) {
            if (item.isAvailable()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    /**
     * Tries to convert a string to a Pattern
     * @param pattern string to convert
     * @return Pattern or null if incorrect string
     */
    private Pattern getPattern(String pattern) {
        pattern = pattern.toUpperCase();
        pattern = pattern.replaceAll(" ", "_");
        try {
            return Pattern.valueOf(pattern);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Converts string to Material
     * @param material string to convert
     * @return the Material or null if not a material
     */
    private Material getMaterial(String material) {
        material = material.toUpperCase();
        try {
            return Material.valueOf(material);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Creates a new tie
     * @param pattern
     * @param material
     * @param brand
     * @param pricePerDay
     * @param length
     * @param width
     * @return
     */
    public String newTie(String pattern, String material, String brand,
                         String color, String pricePerDay, String length, String width) {

        Pattern pPattern = getPattern(pattern);
        if (pPattern == null) {
            return pattern + " is not a valid pattern";
        }
        Material mMaterial = getMaterial(material);
        if (mMaterial == null) {
            return material + " is not a valid material";
        }
        double dLength = getDoubleFromString(length);
        double dWidth = getDoubleFromString(width);
        double dPricePerDay = getDoubleFromString(pricePerDay);

        if (dPricePerDay < 0) {
            return "Not a valid price per day";
        }
        if(dLength < 0) {
            return "Not a valid length";
        }
        if (dWidth < 0) {
            return "Not a valid width";
        }
        Tie tie = new Tie(pPattern, mMaterial, brand, color, dPricePerDay, dLength, dWidth);
        inventory.addItem(tie);
        return "Added tie: " + tie.toString();
    }

    /**
     * Creates a new bowtie
     * @param pattern
     * @param material
     * @param brand
     * @param pricePerDay
     * @param size
     * @param pretied
     * @return
     */
    public String newBowtie(String pattern, String material, String brand,
                            String color, String pricePerDay, String size, String pretied) {

        Pattern pPattern = getPattern(pattern);
        if (pPattern == null) {
            return pattern + " is not a valid level";
        }
        Material mMaterial = getMaterial(material);
        if (mMaterial == null) {
            return material + " is not a valid material";
        }
        double dPricePerDay = getDoubleFromString(pricePerDay);
        if (dPricePerDay < 0) {
            return "Not a valid price per day";
        }
        int iPretied = getBooleanFromString(pretied);
        boolean bPretied;
        if (iPretied == -1) {
            return "Print only y or n for pre-tied";
        } else if ( iPretied == 0) {
            bPretied = false;
        } else {
            bPretied = true;
        }

        Bowtie bowtie = new Bowtie(pPattern, mMaterial, brand, color, dPricePerDay, size, bPretied);
        inventory.addItem(bowtie);
        return "Added bowtie: " + bowtie.toString();
    }

    /**
     * Converts  a string to a double
     * @param string string to convert
     * @return -1 if not a double or not larger than 0
     */
    private double getDoubleFromString(String string) {
        try {
            double i = Double.parseDouble(string);
            if (i > 0) {
                return i;
            }
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Tries to convert a string to a boolean, if the string contains 'Y' it should be true, if
     * string is 'N' it should be false
     * @param string 'y' or 'n' for true or false (not case sensitive)
     * @return -1 if not correct, 0 if false, 1 if true
     */
    private int getBooleanFromString(String string) {
        if (string .equalsIgnoreCase("Y"))
            return 1;
        else if (string.equalsIgnoreCase("N"))
            return 0;
        else return -1;
    }

    /**
     * Removes item
     * @param itemID id of item to remove
     */
/*
    public void removeItem(String itemID) {
        inventory.removeItem(itemID);
    }
*/

    /**
     * Changes status of en item
     * @param item item to change
     * @param available true if available
     */
    public void changeItemAvailable(Item item, boolean available) {
        item.setAvailable(available);
    }
}
