package com.ljuslin.view;

import com.ljuslin.controller.RevenueController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.RevenueException;
import com.ljuslin.model.Item;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Creates revenues tab
 *
 * @author Tina Ljuslin
 */
public class RevenueView extends View implements TabView {
    private RevenueController revenueController;
    private Tab tab;
    private BorderPane pane;
    private VBox vBox;
    private HBox hBox;

    private Button searchItemRevenueButton;
    private Button totalRevenueButton;
    private Button exitButton;
    private Region region;
    private Label totalRevenueLabel;

    public RevenueView() {
    }

    public void setRevenueController(RevenueController revenueController) {
        this.revenueController = revenueController;
    }

    public Tab getTab() {
        tab = new Tab("Ekonomi");
        pane = new BorderPane();
        vBox = new VBox();
        hBox = new HBox();
        totalRevenueButton = new Button("Total vinst");
        searchItemRevenueButton = new Button("Vinst per vara");
        totalRevenueButton.setMaxWidth(Double.MAX_VALUE);
        searchItemRevenueButton.setMaxWidth(Double.MAX_VALUE);
        exitButton = new Button("Avsluta");
        region = new Region();
        totalRevenueLabel = new Label("Total vinst: ");

        vBox.getChildren().addAll(totalRevenueButton, searchItemRevenueButton, region, exitButton);
        hBox.getChildren().addAll(totalRevenueLabel);
        VBox.setVgrow(region, Priority.ALWAYS);

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

    public void updateTotalRevenue() {
        try {
            totalRevenueLabel.setText("Total vinst: " + revenueController.getTotalRevenue() + "kr");
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

            Item item = revenueController.getItemForRevenue();
            totalRevenueLabel.setText(item.toString() + "\nVinst: "
                    + revenueController.getRevenuePerItem(item) + "kr");
        } catch (RevenueException e) {
            showInfoAlert(e.getMessage());
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
}
