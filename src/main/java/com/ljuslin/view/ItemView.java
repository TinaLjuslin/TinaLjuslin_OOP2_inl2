package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Item;
import com.ljuslin.model.Material;
import com.ljuslin.model.Member;
import com.ljuslin.model.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * knappar på vänster sida, lägg till, ta bort, ändra, uppdatera
 *
 */
public class ItemView implements TabView{
    private MainController mainController;

    private Tab tab;
    private BorderPane pane;
    private VBox vbox;
    private Button newTieButton;
    private Button newBowtieButton;
    private Button searchButton;
    private Button changeButton;
    private Button deleteButton;
    private TableView<Item> table;
    private TableColumn<Item, Pattern> patternColumn;
    private TableColumn<Item, Material> materialColumn;
    private TableColumn<Item, String> brandColumn;
    private TableColumn<Item, Double> priceColumn;
    private TableColumn<Item, String> colorColumn;

    public ItemView(){}

    public Tab getTab() {
        tab = new  Tab("Items");
        pane = new BorderPane();
        vbox = new VBox();
        newTieButton = new Button("Ny slips");
        newBowtieButton = new Button("Ny fluga");
        searchButton = new Button("Sök");
        changeButton = new Button("Ändra");
        deleteButton = new Button("Ta bort");
        newTieButton.setMaxWidth(Double.MAX_VALUE);
        newBowtieButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        changeButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);

        vbox.getChildren().addAll(newTieButton, newBowtieButton, searchButton, changeButton, deleteButton);
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
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(patternColumn, colorColumn, materialColumn, brandColumn, priceColumn);
        populateTable();
        pane.setLeft(vbox);
        pane.setCenter(table);
        tab.setContent(pane);
        newTieButton.setOnAction(e -> {
            //mainController.newMemberView();
            populateTable();
        });
        newBowtieButton.setOnAction(ae -> {

        });
        searchButton.setOnAction(e -> {

        });
        changeButton.setOnAction(e -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                //mainController.changeMemberView(member);
                populateTable();
            } else {
                showInfoAlert("Välj en medlem att ändra!");
            }
        });
        deleteButton.setOnAction(ae -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                /*try {
                    //mainController.removeMember(item);
                    populateTable();
                } catch (MemberException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showInfoAlert(e.getMessage());
                }*/
            } else {
                showInfoAlert("Välj en medlem att ta bort!");
            }
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

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Information");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(errorMessage);
        alert.showAndWait();
    }
}
