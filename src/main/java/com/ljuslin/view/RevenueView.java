package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.RevenueException;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class RevenueView extends View implements TabView {
    private MainController mainController;
    private Tab tab;
    private BorderPane pane;
    private VBox vBox;
    private HBox hBox;

    private Button searchItemRevenueButton;
    private Button totalRevenueButton;
    private Button exitButton;
    private Region region;
    private Label totalRevenueInfoLabel;
    private Label totalRevenueLabel;

    public RevenueView() {
    }

    public Tab getTab() {
        tab = new Tab("Ekonomi");
        pane = new BorderPane();
        vBox = new VBox();
        hBox = new HBox();
        totalRevenueButton = new Button("Visa vinst");
        searchItemRevenueButton = new Button("Visa vinst per vara");
        totalRevenueButton.setMaxWidth(Double.MAX_VALUE);
        searchItemRevenueButton.setMaxWidth(Double.MAX_VALUE);
        exitButton = new Button("Avsluta");
        region =  new Region();
        totalRevenueInfoLabel = new Label("Total vinst: ");
        totalRevenueLabel = new Label();

        vBox.getChildren().addAll(totalRevenueButton, searchItemRevenueButton, region, exitButton);
        hBox.getChildren().addAll(totalRevenueInfoLabel, totalRevenueLabel);
        VBox.setVgrow(region, Priority.ALWAYS );

        totalRevenueButton.setOnAction(ae -> {
            updateTotalRevenue();
        });
        searchItemRevenueButton.setOnAction(ae -> {
            updateTotalRevenuePerItem();
        });
        exitButton.setOnAction(ae -> {
            System.exit(0);
        });

        pane.setLeft(vBox);
        pane.setCenter(hBox);
        tab.setContent(pane);
        return tab;
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    public void updateTotalRevenue() {
        try {
            totalRevenueInfoLabel.setText("Total vinst: ");
            totalRevenueLabel.setText(mainController.getTotalRevenue());
        } catch (RevenueException e) {
            showInfoAlert(e.getMessage());
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    public void updateTotalRevenuePerItem() {
        try {
            totalRevenueLabel.setText(mainController.getRevenuePerItem());
            totalRevenueInfoLabel.setText("Total vinst för vald vara:");
        } catch (RevenueException e) {
            showInfoAlert(e.getMessage());
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
}
