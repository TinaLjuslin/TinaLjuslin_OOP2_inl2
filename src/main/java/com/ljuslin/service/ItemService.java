package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
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

    public ItemService(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getItem(String itemID) throws FileException{
        try {
            for (Item item : inventory.getItems()) {
                if (item.getItemID().equals(itemID)) {
                    return item;
                }
            }
        } catch (FileException e) {
            throw e;
        }
        return null;
    }

    public List<Item> searchItem(String search) throws FileException, ItemException {
        List<Item> items;
        try {
            items = inventory.getItems();
        } catch (FileException e) {
            throw e;
        }
        if (items == null || items.isEmpty()) {
            throw new ItemException("Item could not be found");
        }
        List<Item> searchtems = new ArrayList<>();
        for (Item item : items) {
            if (item.toString().toLowerCase().contains(search.toLowerCase())) {
                searchtems.add(item);
            }
        }
        return searchtems;
    }

    public List<Item> getItems() throws FileException {
        try {
            return inventory.getItems();
        } catch (FileException e) {
            throw e;
        }
    }

    public List<Item> getAvailableItems() throws FileException {
        List<Item> availableItems = new ArrayList<>();
        try {
            for (Item item : inventory.getItems()) {
                if (item.isAvailable()) {
                    availableItems.add(item);
                }
            }
        } catch (FileException e) {
            throw e;
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

    public void newTie(Pattern pattern, Material material, String brand,
                         String color, String sPricePerDay, String sLength, String sWidth)
            throws FileException, ItemException {
        double pricePerDay, width, length;
        try {
            pricePerDay = Double.parseDouble(sPricePerDay);
            if (pricePerDay < 0) {
                throw new ItemException("Pris per dag måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Pris per dag måste vara en positiv siffra");
        }
        try {
            width = Double.parseDouble(sWidth);
            if (width < 0) {
                throw new ItemException("Bredd måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Bredd måste vara en positiv siffra");
        }
        try {
            length = Double.parseDouble(sLength);
            if (length < 0) {
                throw new ItemException("Längd måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Längd måste vara en positiv siffra");
        }
        Tie tie = new Tie(pattern, material, brand, color, pricePerDay, length, width);
        try {
            inventory.addItem(tie);
        } catch (FileException e) {
            throw e;
        }
    }

    public void newBowtie(Pattern pattern, Material material, String brand,
                            String color, String sPricePerDay, String size, boolean pretied)
            throws FileException, ItemException {
        double pricePerDay;
        try {
            pricePerDay = Double.parseDouble(sPricePerDay);
            if (pricePerDay < 0) {
                throw new ItemException("Pris per dag måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Pris per dag måste vara en positiv siffra");
        }
        Bowtie bowtie = new Bowtie(pattern, material, brand, color, pricePerDay, size, pretied);
        try {
            inventory.addItem(bowtie);
        } catch (FileException e) {
            throw e;
        }
    }

    public void removeItem(Item item) throws FileException, ItemException {
        inventory.removeItem(item);
    }

    public void changeItemAvailable(Item item, boolean available) throws FileException, ItemException {
        item.setAvailable(available);
        inventory.changeItem(item);
    }

    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String sWidth, String sLength)
            throws FileException, ItemException {
        double pricePerDay, width, length;
        try {
            pricePerDay = Double.parseDouble(sPricePerDay);
            if (pricePerDay < 0) {
                throw new ItemException("Pris per dag måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Pris per dag måste vara en positiv siffra");
        }
        try {
            width = Double.parseDouble(sWidth);
            if (width < 0) {
                throw new ItemException("Bredd måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Bredd måste vara en positiv siffra");
        }
        try {
            length = Double.parseDouble(sLength);
            if (length < 0) {
                throw new ItemException("Längd måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Längd måste vara en positiv siffra");
        }
        item.setBrand(brand);
        item.setColor(color);
        item.setMaterial(material);
        item.setPattern(pattern);
        item.setPricePerDay(pricePerDay);
        ((Tie)item).setWidth(width);
        ((Tie)item).setLength(length);
        inventory.changeItem(item);
    }

    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String size, boolean preTied)
            throws FileException, ItemException {
        double pricePerDay, width, length;
        try {
            pricePerDay = Double.parseDouble(sPricePerDay);
            if (pricePerDay < 0) {
                throw new ItemException("Pris per dag måste vara en positiv siffra");
            }
        } catch (NumberFormatException e) {
            throw new ItemException("Pris per dag måste vara en positiv siffra");
        }
        item.setBrand(brand);
        item.setColor(color);
        item.setMaterial(material);
        item.setPattern(pattern);
        item.setPricePerDay(pricePerDay);
        ((Bowtie)item).setSize(size);
        ((Bowtie)item).setPreTied(preTied);
        inventory.changeItem(item);
    }
}
