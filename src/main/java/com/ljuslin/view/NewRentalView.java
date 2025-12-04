package com.ljuslin.view;

import com.ljuslin.controller.ItemController;
import com.ljuslin.controller.MemberController;
import com.ljuslin.controller.RentalController;
import com.ljuslin.exception.FileException;
import com.ljuslin.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class NewRentalView extends View {
    //private RentalController rentalController;
    private MemberController memberController;
    private ItemController itemController;

    private BorderPane pane;
    private Stage newRentalStage;
    private Scene scene2;
    private VBox vbox;
    private Button chooseButton;
    private Button cancelButton;
    private TableView<Member> memberTable;
    private TableView<Item> itemTable;
    private TableColumn<Member, String> idColumn;
    private TableColumn<Member, String> firstNameColumn;
    private TableColumn<Member, String> lastNameColumn;
    private TableColumn<Member, Level> levelColumn;
    private TableColumn<Item, Pattern> patternColumn;
    private TableColumn<Item, Material> materialColumn;
    private TableColumn<Item, String> brandColumn;
    private TableColumn<Item, Double> priceColumn;
    private TableColumn<Item, String> colorColumn;
    private TableColumn<Item, String> sizeColumn;
    private TableColumn<Item, String> preeTiedColumn;
    private TableColumn<Item, Double> widthColumn;
    private TableColumn<Item, Double> lengthColumn;

    private Member member;
    private Item item;

    public NewRentalView( MemberController memberController,
                         ItemController itemController) {
        //this.rentalController = rentalController;
        this.memberController = memberController;
        this.itemController = itemController;
    }

    public Member showMemberPopUp(Stage mainStage, Scene mainScene) {
        newRentalStage = new Stage();
        pane = new BorderPane();
        vbox = new VBox();

        chooseButton = new Button("Välj");
        cancelButton = new Button("Avbryt");
        chooseButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().addAll(chooseButton, cancelButton);
        memberTable = new TableView<>();
        memberTable.setEditable(false);
        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        firstNameColumn = new TableColumn<>("Förnamn");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn = new TableColumn<>("Efternamn");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("memberLevel"));
        memberTable.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, levelColumn);
        populateMemberTable();
        pane.setLeft(vbox);
        pane.setCenter(memberTable);
        scene2 = new Scene(pane, 1000, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        chooseButton.setOnAction(ae -> {
            Member tempMember = memberTable.getSelectionModel().getSelectedItem();
            if (tempMember != null) {
                this.member = tempMember;
                newRentalStage.close();

            } else {
                showInfoAlert("Välj en medlem som ska hyra!");
            }
        });
        cancelButton.setOnAction(ae -> {
            newRentalStage.close();
        });

        newRentalStage.initOwner(mainStage);
        newRentalStage.initModality(Modality.APPLICATION_MODAL);
        newRentalStage.setScene(scene2);
        newRentalStage.showAndWait();
        return member;
    }

    public Item showAvailableItemPopUp(Stage mainStage, Scene mainScene) {
        newRentalStage = new Stage();
        pane = new BorderPane();
        vbox = new VBox();

        chooseButton = new Button("Välj");
        cancelButton = new Button("Avbryt");
        chooseButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().clear();
        vbox.getChildren().addAll(chooseButton, cancelButton);
        itemTable = new TableView<>();
        itemTable.setEditable(false);
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

        itemTable.getColumns().addAll(patternColumn, colorColumn, materialColumn, brandColumn,
                priceColumn, sizeColumn, preeTiedColumn, widthColumn, lengthColumn);
        populateAvailableItemTable();
        pane.setLeft(vbox);
        pane.setCenter(itemTable);

        scene2 = new Scene(pane, 1000, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        chooseButton.setOnAction(ae -> {
            Item tempItem = itemTable.getSelectionModel().getSelectedItem();
            if (tempItem != null) {
                this.item = tempItem;
                newRentalStage.close();
            } else {
                showInfoAlert("Välj en item att hyra!");
            }
        });
        cancelButton.setOnAction(ae -> {
            newRentalStage.close();
        });

        newRentalStage.initOwner(mainStage);
        newRentalStage.initModality(Modality.APPLICATION_MODAL);
        newRentalStage.setScene(scene2);
        newRentalStage.showAndWait();
        return item;
    }

    public Item showAllItemPopUp(Stage mainStage, Scene mainScene) {
        newRentalStage = new Stage();
        pane = new BorderPane();
        vbox = new VBox();

        chooseButton = new Button("Välj");
        cancelButton = new Button("Avbryt");
        chooseButton.setMaxWidth(Double.MAX_VALUE);
        cancelButton.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().clear();
        vbox.getChildren().addAll(chooseButton, cancelButton);
        itemTable = new TableView<>();
        itemTable.setEditable(false);
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

        itemTable.getColumns().addAll(patternColumn, colorColumn, materialColumn, brandColumn,
                priceColumn, sizeColumn, preeTiedColumn, widthColumn, lengthColumn);
        populateAllItemTable();
        pane.setLeft(vbox);
        pane.setCenter(itemTable);

        scene2 = new Scene(pane, 1000, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        chooseButton.setOnAction(ae -> {
            Item tempItem = itemTable.getSelectionModel().getSelectedItem();
            if (tempItem != null) {
                this.item = tempItem;
                newRentalStage.close();
            } else {
                showInfoAlert("Välj en item att hyra!");
            }
        });
        cancelButton.setOnAction(ae -> {
            newRentalStage.close();
        });

        newRentalStage.initOwner(mainStage);
        newRentalStage.initModality(Modality.APPLICATION_MODAL);
        newRentalStage.setScene(scene2);
        newRentalStage.showAndWait();
        return item;
    }

    private void populateMemberTable() {
        try {
            List<Member> list = memberController.getAllMembers();
            ObservableList<Member> observableList = FXCollections.observableList(list);
            memberTable.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    private void populateAvailableItemTable() {
        try {
            List<Item> list = itemController.getAllAvailableItems();
            ObservableList<Item> observableList = FXCollections.observableList(list);
            itemTable.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    private void populateAllItemTable() {
        try {
            List<Item> list = itemController.getAllItems();
            ObservableList<Item> observableList = FXCollections.observableList(list);
            itemTable.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
}
