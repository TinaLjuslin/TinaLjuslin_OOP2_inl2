package com.ljuslin.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.model.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds all items in this rental shop
 *
 * @author Tina Ljuslin
 */
public class Inventory {
    private final String FILENAME = "items.json";
    private ObjectMapper mapper = new ObjectMapper();
    private final File itemFile = new File(FILENAME);

    public Inventory() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();

        module.registerSubtypes(
                new NamedType(Tie.class, "Tie"),
                new NamedType(Bowtie.class, "Bowtie")
        );
        mapper.registerModule(module);
        mapper.enable(MapperFeature.USE_ANNOTATIONS);
        if (!itemFile.exists()) {
            try {
                mapper.writeValue(itemFile, new ArrayList<Item>());
            } catch (IOException e) {
                System.err.println("VARNING: Kunde inte skapa initial medlemsfil.");
            }
        }
    }

    private void saveItems(List<Item> items) throws FileException {
        try {
            mapper.writerFor(new TypeReference<List<Item>>() {})
                    .writeValue(itemFile, items);
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


    public void addItem(Item item) throws FileException {
        try {
            List<Item> items = getItems();
            items.add(item);
            saveItems(items);
        } catch (Exception e) {
            throw new FileException("Den nya varan kunde ej sparas");
        }
    }

    public void removeItem(Item item) throws FileException, ItemException {
        List<Item> items;
        try {
            items = getItems();
        } catch (FileException ex) {
            throw new FileException("Filen kunde inte läsas");
        }
        boolean removed = false;
        for (Item i : items) {
            if (i.getItemID() == item.getItemID()) {
                items.remove(i);
                removed = true;
                break;
            }
        }
        if (!removed) {
            throw new ItemException("Item could not be found!");
        }
        saveItems(items);
    }
    public void changeItem(Item item) throws FileException, ItemException {
        List<Item> items;
        try {
            items = getItems();
        } catch (FileException ex) {
            throw new FileException("Filen kunde inte läsas");
        }
        boolean changed = false;
        for (Item i : items) {
            if (i.getItemID().equals(item.getItemID())) {
                items.remove(i);
                items.add(item);
                changed = true;
                break;
            }
        }
        if (!changed) {
            throw new ItemException("Item could not be found!");
        }
        saveItems(items);
    }
}
