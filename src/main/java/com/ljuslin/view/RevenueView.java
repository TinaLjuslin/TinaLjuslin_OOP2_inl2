package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import javafx.scene.control.Tab;

public class RevenueView implements TabView{
    private MainController mainController;
    private Tab tab;
    public RevenueView() {}
    public Tab getTab() {
        tab = new Tab("Item");
        return tab;
    }
    public void setController(MainController mainController) {
        this.mainController = mainController;
    }
}
