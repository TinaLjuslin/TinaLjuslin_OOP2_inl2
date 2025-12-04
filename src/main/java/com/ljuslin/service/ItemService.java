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
 *
 * @author Tina Ljuslin
 */
public class ItemService {
    private Inventory inventory;

    public ItemService(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getItem(String itemID) throws FileException {
        for (Item item : inventory.getItems()) {
            if (item.getItemID().equals(itemID)) {
                return item;
            }
        }

        return null;
    }

    public List<Item> searchItem(String search) throws FileException, ItemException {
        List<Item> searchItems = inventory.getItems().stream()
                .filter(item -> item.toString().toLowerCase().contains(search.toLowerCase()))
                .toList();
        if (searchItems == null || searchItems.isEmpty()) {
            throw new ItemException("Inga varor funna");
        }
        return searchItems;
    }

    public List<Item> getItems() throws FileException {
        return inventory.getItems();

    }

    public List<Item> getAvailableItems() throws FileException, ItemException {
        List<Item> availableItems = inventory.getItems().stream()
                .filter(item -> item.isAvailable())
                .toList();
        if (availableItems == null || availableItems.isEmpty()) {
            throw new ItemException("Inga tillgängliga varor funna");
        }
        return availableItems;
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
        inventory.addItem(tie);
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
        inventory.addItem(bowtie);

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
        ((Tie) item).setWidth(width);
        ((Tie) item).setLength(length);
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
        ((Bowtie) item).setSize(size);
        ((Bowtie) item).setPreTied(preTied);
        inventory.changeItem(item);
    }
}
