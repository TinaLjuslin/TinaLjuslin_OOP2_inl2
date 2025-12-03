package com.ljuslin.view;

import com.ljuslin.controller.ItemController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.model.Material;
import com.ljuslin.model.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NewItemView extends View {
    private ItemController itemController;

    private Stage newItemStage;
    private Scene scene2;
    private TextField brandField;
    private TextField colorField;
    private TextField pricePerDayField;
    private TextField lengthField;
    private TextField widthField;
    private TextField sizeField;

    private ComboBox<Pattern> patternComboBox;
    private ComboBox<Material> materialComboBox;
    private ComboBox<Boolean> preeTiedComboBox;

    private HBox hBox;
    private Label brandLabel;
    private Label colorLabel;
    private Label materialLabel;
    private Label patternLabel;
    private Label pricePerDayLabel;
    private Label widthLabel;
    private Label lengthLabel;
    private Label sizeLabel;
    private Label preeTiedLabel;
    private Button saveTieButton;
    private Button saveBowtieButton;
    private Button cancelButton;
    private Button tieButton;
    private Button bowtieButton;
    private GridPane gridPane;
    public NewItemView(ItemController itemController) {
        this.itemController = itemController;
    }

    public void showPopUp(Stage mainStage, Scene mainScene) {
        newItemStage = new Stage();
        tieButton = new Button("Slips");
        bowtieButton = new Button("Fluga");
        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(tieButton, bowtieButton);

        saveTieButton = new Button("Spara");
        saveBowtieButton = new Button("Spara");
        cancelButton = new Button("Avbryt");
        brandLabel = new Label("Märke");
        colorLabel = new Label("Färg");
        materialLabel = new Label("Material");
        patternLabel = new Label("Mönster");
        pricePerDayLabel = new Label("Pris per dag");
        widthLabel = new Label("Bredd");
        lengthLabel = new Label("Längd");
        sizeLabel = new Label("Storlek");
        preeTiedLabel = new Label("Färdigknuten");

        brandField = new TextField();
        colorField = new TextField();
        widthField = new TextField();
        lengthField = new TextField();
        pricePerDayField = new TextField();
        sizeField = new TextField();
        ObservableList<Material> materials = FXCollections.observableArrayList(Material.values());
        materialComboBox = new ComboBox<>(materials);
        materialComboBox.getSelectionModel().select(0);
        ObservableList<Pattern> patterns = FXCollections.observableArrayList(Pattern.values());
        patternComboBox = new ComboBox<>(patterns);
        patternComboBox.getSelectionModel().select(0);
        ObservableList<Boolean> pre = FXCollections.observableArrayList(Boolean.FALSE, Boolean.TRUE);
        preeTiedComboBox = new ComboBox<>(pre);
        preeTiedComboBox.getSelectionModel().select(0);

        gridPane = new GridPane();
        gridPane.add(brandLabel, 0, 0);
        gridPane.add(colorLabel, 0, 1);
        gridPane.add(patternLabel, 0, 2);
        gridPane.add(materialLabel, 0, 3);
        gridPane.add(pricePerDayLabel, 0, 4);
        gridPane.add(brandField, 1, 0);
        gridPane.add(colorField, 1, 1);
        gridPane.add(patternComboBox, 1, 2);
        gridPane.add(materialComboBox, 1, 3);
        gridPane.add(pricePerDayField, 1, 4);
        gridPane.add(cancelButton, 2, 7);
        scene2 = new Scene(hBox, 500, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        tieButton.setOnAction(e -> {
            newTie();
        });
        bowtieButton.setOnAction(e -> {
            newBowtie();
        });
        saveTieButton.setOnAction( ae -> {
            try {
                itemController.newTie(brandField.getText(), colorField.getText(),
                        materialComboBox.getValue(), patternComboBox.getValue(),
                        pricePerDayField.getText(), widthField.getText(), lengthField.getText());
                newItemStage.close();
            } catch (ItemException e) {
                showInfoAlert(e.getMessage());
            } catch (FileException e) {
                showInfoAlert(e.getMessage());
            } catch (Exception e) {
                showErrorAlert(e.getMessage());
            }
        });
        saveBowtieButton.setOnAction(ae -> {
            try {
                itemController.newBowtie(brandField.getText(), colorField.getText(),
                        materialComboBox.getValue(), patternComboBox.getValue(),
                        pricePerDayField.getText(), sizeField.getText(), preeTiedComboBox.getValue());
                newItemStage.close();
            } catch (ItemException e) {
                showInfoAlert(e.getMessage());
            } catch (FileException e) {
                showInfoAlert(e.getMessage());
            } catch (Exception e) {
                showErrorAlert(e.getMessage());
            }
        });
        cancelButton.setOnAction(ae -> {
            newItemStage.close();
        });

        newItemStage.initOwner(mainStage);
        newItemStage.initModality(Modality.APPLICATION_MODAL);
        newItemStage.setScene(scene2);
        newItemStage.showAndWait();
    }

    private void newTie() {
        gridPane.add(widthLabel, 0, 5);
        gridPane.add(widthField, 1, 5);
        gridPane.add(lengthLabel, 0, 6);
        gridPane.add(lengthField, 1, 6);
        gridPane.add(saveTieButton, 1, 7);

        scene2 = new Scene(gridPane, 500, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        newItemStage.setScene(scene2);
    }
    private void newBowtie() {
        gridPane.add(preeTiedLabel, 0, 5);
        gridPane.add(preeTiedComboBox, 1, 5);
        gridPane.add(sizeLabel, 0, 6);
        gridPane.add(sizeField, 1, 6);
        gridPane.add(saveBowtieButton, 1, 7);

        scene2 = new Scene(gridPane, 500, 500);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        newItemStage.setScene(scene2);
    }
}
