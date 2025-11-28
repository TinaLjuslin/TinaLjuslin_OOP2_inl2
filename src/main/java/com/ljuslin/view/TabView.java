package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import javafx.scene.control.Tab;

public interface TabView {
    public Tab getTab();
    public void setController(MainController mainController);
}
