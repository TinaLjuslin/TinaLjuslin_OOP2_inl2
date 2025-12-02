package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ItemView extends View implements TabView {
    private MainController mainController;

    private Tab tab;
    private BorderPane pane;
    private VBox vbox;
    private Button newItemButton;
    private Button searchButton;
    private Button changeButton;
    private Button deleteButton;
    private Button rechargeButton;
    private TableView<Item> table;
    private TableColumn<Item, Pattern> patternColumn;
    private TableColumn<Item, Material> materialColumn;
    private TableColumn<Item, String> brandColumn;
    private TableColumn<Item, Double> priceColumn;
    private TableColumn<Item, String> colorColumn;
    private TableColumn<Item, String> sizeColumn;
    private TableColumn<Item, String> preeTiedColumn;
    private TableColumn<Item, Double> widthColumn;
    private TableColumn<Item, Double> lengthColumn;
    private TableColumn<Item, Double> availableColumn;

    public ItemView() {
    }

    public Tab getTab() {
        tab = new Tab("Items");
        pane = new BorderPane();
        vbox = new VBox();
        newItemButton = new Button("Ny");
        searchButton = new Button("Sök");
        changeButton = new Button("Ändra");
        deleteButton = new Button("Ta bort");
        rechargeButton = new Button("Ladda om");
        newItemButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        changeButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        rechargeButton.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().addAll(newItemButton, searchButton, changeButton, deleteButton,
                rechargeButton);
        table = new TableView<>();
        table.setEditable(false);
        patternColumn = new TableColumn<>("Mönster");
        patternColumn.setCellValueFactory(new PropertyValueFactory<>("pattern"));
        colorColumn = new TableColumn<>("färg");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        materialColumn = new TableColumn<>("Material");
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        brandColumn = new TableColumn<>("Märke");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));
        priceColumn = new TableColumn<>("Pris per dag");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerDay"));
        preeTiedColumn = new TableColumn<>("Färdigknuten");
        preeTiedColumn.setCellValueFactory(new PropertyValueFactory<>("preTied"));
        sizeColumn = new TableColumn<>("Storlek");
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        widthColumn = new TableColumn<>("Bredd");
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        lengthColumn = new TableColumn<>("Längd");
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));
        availableColumn = new TableColumn<>("Tillgänglig");
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));

        table.getColumns().addAll(patternColumn, colorColumn, materialColumn, brandColumn,
                priceColumn, sizeColumn, preeTiedColumn, widthColumn, lengthColumn, availableColumn);
        populateTable();
        pane.setLeft(vbox);
        pane.setCenter(table);
        tab.setContent(pane);
        newItemButton.setOnAction(e -> {
            mainController.newItemView();
            populateTable();
        });
        searchButton.setOnAction(e -> {
            mainController.searchItemView();
        });
        changeButton.setOnAction(e -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                mainController.changeItemView(item);
                populateTable();
            } else {
                showInfoAlert("Välj en medlem att ändra!");
            }
        });
        deleteButton.setOnAction(ae -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                try {
                    mainController.removeItem(item);
                    populateTable();
                } catch (ItemException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showInfoAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en vara att ta bort!");
            }
        });
        rechargeButton.setOnAction(ae ->{
            populateTable();
        });

        return tab;
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    private void populateTable() {
        try {
            List<Item> list = mainController.getAllItems();
            ObservableList<Item> observableList = FXCollections.observableList(list);
            table.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    public void populateTable(List<Item> items) {
        try {
            ObservableList<Item> observableList = FXCollections.observableList(items);
            table.setItems(observableList);
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
}
