package com.ljuslin.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ljuslin.exception.FileException;
import com.ljuslin.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Holds all items in this rental shop
 * @author Tina Ljuslin
 */
public class Inventory {
    private final String FILENAME = "items.json";
    private ObjectMapper mapper = new ObjectMapper();
    private final File itemFile = new File(FILENAME);
    public Inventory() {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            if (!itemFile.exists()) {
                try {
                    mapper.writeValue(itemFile, new ArrayList<Item>());
                } catch (IOException e) {
                    System.err.println("VARNING: Kunde inte skapa initial medlemsfil.");
                }
            }
        }
        /*items.add(new Tie(Pattern.PAISLEY, Material.SILK, "Slipstillverkaren AB", "yellow", 37, 127,
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
                false));*/

    private void saveItems(List<Item> items) throws FileException {
        try {
            mapper.writeValue(itemFile, items);
        } catch (IOException e) {
            throw new FileException("Kunde ej spara medlemmar till fil");
        }
    }

    public List<Item> getItems() throws FileException {
        try {
            return new ArrayList<>(Arrays.asList(mapper.readValue(itemFile,
                    Item[].class)));

        } catch (Exception e) {
            throw new FileException("Itemsfilen kunde ej läsas");
        }
    }


    public void addItem(Item item) throws FileException{
        try {
            List<Item> items = getItems();
            items.add(item);
            mapper.writeValue(new File(FILENAME), items);
        } catch (Exception e) {
            throw new FileException("Den nya medlemmen kunde ej sparas");
        }}

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
