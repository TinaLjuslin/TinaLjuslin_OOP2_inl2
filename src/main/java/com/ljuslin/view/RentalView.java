package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import javafx.scene.control.Tab;

public class RentalView extends View implements TabView{
    private MainController mainController;
    private Tab tab;
    public RentalView() {}
    public Tab getTab() {
        tab = new Tab("Item");
        return tab;
    }
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }
}
