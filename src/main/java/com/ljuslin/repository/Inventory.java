package com.ljuslin.repository;

import com.ljuslin.model.Bowtie;
import com.ljuslin.model.Item;
import com.ljuslin.model.Tie;
import com.ljuslin.model.Material;
import com.ljuslin.model.Pattern;

import java.util.ArrayList;
import java.util.List;
/**
 * Holds all items in this rental shop
 * @author Tina Ljuslin
 */
public class Inventory {
    private List<Item> items = new ArrayList<>();

    /**
     * Constructor, creates items for testing
     */
    public Inventory() {
        items.add(new Tie(Pattern.PAISLEY, Material.SILK, "Slipstillverkaren AB", "yellow", 37, 127,
                8));
        items.add(new Tie(Pattern.DOTTED, Material.POLYESTER, "Slipstillverkaren AB", "blue", 15,
                130,
                10));
        items.add(new Tie(Pattern.PAISLEY, Material.WOOL, "NewTies AB", "red", 32, 129,
                7));
        items.add(new Tie(Pattern.STRIPED, Material.SILK, "NewTies AB", "purple", 29, 122,
                8));
        items.add(new Tie(Pattern.NO_PATTERN, Material.POLYESTER, "Slipstillverkaren AB", "blue",
                37, 127,
                8.5));
        items.add(new Bowtie(Pattern.NO_PATTERN, Material.POLYESTER, "NewTies AB", "yellow", 15,
                "s", true));
        items.add(new Bowtie(Pattern.DOTTED, Material.SILK, "NewTies AB", "red", 35, "s", false));
        items.add(new Bowtie(Pattern.NO_PATTERN, Material.SILK, "NewTies AB", "green", 35, "m",
                false));
        items.add(new Bowtie(Pattern.STRIPED, Material.SILK, "NewTies AB", "pink", 35, "l", false));
        items.add(new Bowtie(Pattern.PAISLEY, Material.COTTON, "Slipstillverkaren AB", "blue",28
                , "m",
                false));
        items.add(new Bowtie(Pattern.DOTTED, Material.WOOL, "Slipstillverkaren AB", "purple", 32,
                "xl",
                false));
        items.add(new Bowtie(Pattern.DOTTED, Material.WOOL, "Slipstillverkaren AB", "purple", 10,
                "xl",
                false));
    }

    /**
     * Returns all items
     * @return List of all items
     */
    public List<Item> getItems() {
        return items;
    }


    /**
     * Adds an item
     * @param item item to add
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item
     * @param itemID id of item to remove
     * @return item removed or null if item could not be removed
     */
    public Item removeItem(String itemID) {
        for (Item i : items) {
            if (i.getItemID().equals(itemID)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }
}
