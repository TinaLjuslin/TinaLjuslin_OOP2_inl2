package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.model.Item;
import com.ljuslin.model.Material;
import com.ljuslin.model.Pattern;
import com.ljuslin.service.ItemService;
import com.ljuslin.view.*;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.util.List;

/**
 * Controlls all Views for Items and calls the right service to perform operations
 *
 * @author Tina Ljuslin
 */
public class ItemController {
    private ItemService itemService;
    private ItemView itemView;
    private NewItemView newTieView;
    private ChangeItemView changeItemView;
    private SearchItemView searchItemView;
    private Stage stage;
    private Scene scene;

    public ItemController() {
    }

    public ItemController(ItemService itemService, ItemView itemView) {
        this.itemService = itemService;
        this.itemView = itemView;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Tab getTab() {
        return itemView.getTab();
    }

    public void populateTable() {
        try {
            itemView.populateTable(itemService.getItems());
        } catch (FileException e) {
            itemView.showInfoAlert(e.getMessage());
        }
    }

    public List<Item> getAllItems() throws FileException {
        return itemService.getItems();
    }

    public List<Item> getAllAvailableItems() throws FileException, ItemException {
        return itemService.getAvailableItems();
    }

    public void newItemView() {
        newTieView = new NewItemView(this);
        newTieView.showPopUp(stage, scene);
    }

    public void newTie(String brand, String color, Material material, Pattern pattern,
                       String pricePerDay, String width, String length)
            throws FileException, ItemException {
        itemService.newTie(pattern, material, brand, color, pricePerDay, length, width);
    }

    public void newBowtie(String brand, String color, Material material, Pattern pattern,
                          String pricePerDay, String size, String preTied)
            throws FileException, ItemException {
        itemService.newBowtie(pattern, material, brand, color, pricePerDay, size, preTied);
    }

    public void removeItem(Item item) throws FileException, ItemException {
        itemService.removeItem(item);
    }

    public void changeItemView(Item item) {
        changeItemView = new ChangeItemView(this);
        changeItemView.showPopUp(stage, scene, item);
    }

    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String sWidth, String sLength) throws FileException, ItemException {
        itemService.changeItem(item, brand, color, material, pattern,
                sPricePerDay, sWidth, sLength);
    }

    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String sSize, boolean preTied) throws FileException,
            ItemException {
        itemService.changeItem(item, brand, color, material, pattern, sPricePerDay, sSize, preTied);
    }

    public void searchItemView() {
        searchItemView = new SearchItemView(this);
        searchItemView.showPopUp(stage, scene);
    }

    public void searchItem(String search)
            throws FileException, ItemException {
        List<Item> searchItems = itemService.searchItem(search);
        itemView.populateTable(searchItems);
    }
}
