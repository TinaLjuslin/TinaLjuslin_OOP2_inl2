package com.ljuslin.controller;

import com.ljuslin.exception.*;
import com.ljuslin.model.*;
import com.ljuslin.service.ItemService;
import com.ljuslin.service.MembershipService;
import com.ljuslin.service.RentalService;
import com.ljuslin.service.RevenueService;
import com.ljuslin.view.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.List;

/**
 *
 * @author Tina Ljuslin
 */
public class MainController {

    private MemberController memberController;
    private ItemController itemController;
    private RentalController rentalController;
    private RevenueController revenueController;

    private Stage stage;
    private Scene scene;
    private TabPane tabPane;
    private Tab memberTab;
    private Tab itemTab;
    private Tab rentalTab;
    private Tab revenueTab;

    public MainController() {
    }

    public MainController(ItemService itemService, MembershipService membershipService,
                          RentalService rentalService, RevenueService revenueService,
                          MemberController memberController, ItemController itemController,
                          RentalController rentalController, RevenueController revenueController,
                          ItemView itemView, MemberView memberView,
                          RentalView rentalView, RevenueView revenueView) {
/*
        this.itemService = itemService;
        this.membershipService = membershipService;
        this.rentalService = rentalService;
        this.revenueService = revenueService;
*/
        this.memberController = memberController;
        this.itemController = itemController;
        this.rentalController = rentalController;
        this.revenueController = revenueController;


/*
        this.itemView = itemView;
        this.memberView = memberView;
        this.rentalView = rentalView;
        this.revenueView = revenueView;
*/
    }

    public void start(Stage stage) {
        this.stage = stage;
        memberController.setStage(stage);
        itemController.setStage(stage);
        rentalController.setStage(stage);
        revenueController.setStage(stage);
        memberController.setScene(scene);
        itemController.setScene(scene);
        rentalController.setScene(scene);
        revenueController.setScene(scene);

        tabPane = new TabPane();
        scene = new Scene(tabPane, 1000, 600);
        Tab memberTab = memberController.getTab();
        memberTab.setClosable(false);
        Tab itemTab = itemController.getTab();
        memberTab.setClosable(false);
        Tab rentalTab = rentalController.getTab();
        memberTab.setClosable(false);
        Tab revenueTab = revenueController.getTab();
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
                if (newTab != null) {
                    String tabTitle = newTab.getText();
                    tabClick(tabTitle);
                }
            }
        });
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene.getStylesheets().add(css);
        tabPane.getTabs().addAll(memberTab, itemTab, rentalTab, revenueTab);
        stage.setScene(scene);
        stage.show();
    }

    private void tabClick(String tabName) {
        switch (tabName) {
            case "Medlemmar":
                memberController.populateTable();
                break;
            case "Varor":
                itemController.populateTable();
                break;
            case "Uthyrningar":
                rentalController.populateTable();
                break;
            case "Ekonomi":
                revenueController.populateTable();
        }

    }
}
