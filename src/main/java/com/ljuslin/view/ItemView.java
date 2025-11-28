package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import javafx.scene.control.Tab;

/**
 * knappar på vänster sida, lägg till, ta bort, ändra, uppdatera
 *
 */
public class ItemView implements TabView{
    private MainController mainController;
    private Tab tab;
    public ItemView() {}
    public Tab getTab() {
        tab = new Tab("Item");
        return tab;
    }
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }
}
