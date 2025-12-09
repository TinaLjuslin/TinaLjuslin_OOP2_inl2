package com.ljuslin.view;

import com.ljuslin.controller.ItemController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChangeItemView extends View {
    private ItemController itemController;

    private Stage newItemStage;
    private TextField brandField;
    private TextField colorField;
    private TextField pricePerDayField;
    private TextField lengthField;
    private TextField widthField;
    private TextField sizeField;

    private ComboBox<Pattern> patternComboBox;
    private ComboBox<Material> materialComboBox;
    private ComboBox<Boolean> preeTiedComboBox;


    private Label brandLabel;
    private Label colorLabel;
    private Label materialLabel;
    private Label patternLabel;
    private Label pricePerDayLabel;
    private Label widthLabel;
    private Label lengthLabel;
    private Label sizeLabel;
    private Label preeTiedLabel;
    private Button saveButton;
    private Button cancelButton;
    private GridPane gridPane;
    private Scene scene2;

    public ChangeItemView() {
    }

    public ChangeItemView(ItemController itemController) {
        this.itemController = itemController;
    }

    public void showPopUp(Stage mainStage, Scene mainScene, Item item) {
        newItemStage = new Stage();
        saveButton = new Button("Spara");
        cancelButton = new Button("Avbryt");
        brandLabel = new Label("Märke");
        colorLabel = new Label("Färg");
        materialLabel = new Label("Material");
        patternLabel = new Label("Mönster");
        pricePerDayLabel = new Label("Pris per dag");
        brandField = new TextField();
        brandField.setText(item.getBrand());
        colorField = new TextField();
        colorField.setText(item.getColor());
        pricePerDayField = new TextField();
        pricePerDayField.setText(String.valueOf(item.getPricePerDay()));
        ObservableList<Material> materials = FXCollections.observableArrayList(Material.values());
        materialComboBox = new ComboBox<>(materials);
        materialComboBox.getSelectionModel().select(item.getMaterial());
        ObservableList<Pattern> patterns = FXCollections.observableArrayList(Pattern.values());
        patternComboBox = new ComboBox<>(patterns);
        patternComboBox.getSelectionModel().select(item.getPattern());
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
        gridPane.add(saveButton, 0, 7);
        gridPane.add(cancelButton, 1, 7);
        scene2 = new Scene(gridPane, 500, 500);
        newItemStage.setScene(scene2);

        if (item instanceof Tie) {
            widthLabel = new Label("Bredd");
            lengthLabel = new Label("Längd");
            widthField = new TextField(String.valueOf(((Tie) item).getWidth()));
            lengthField = new TextField(String.valueOf(((Tie) item).getLength()));
            gridPane.add(widthLabel, 0, 5);
            gridPane.add(widthField, 1, 5);
            gridPane.add(lengthLabel, 0, 6);
            gridPane.add(lengthField, 1, 6);

        } else {
            sizeLabel = new Label("Storlek");
            preeTiedLabel = new Label("Färdigknuten");
            sizeField = new TextField();
            sizeField.setText(String.valueOf(((Bowtie) item).getSize()));
            ObservableList<Boolean> pre = FXCollections.observableArrayList(Boolean.FALSE, Boolean.TRUE);
            preeTiedComboBox = new ComboBox<>(pre);
            preeTiedComboBox.getSelectionModel().select(((Bowtie) item).isPreTied());
            gridPane.add(preeTiedLabel, 0, 5);
            gridPane.add(preeTiedComboBox, 1, 5);
            gridPane.add(sizeLabel, 0, 6);
            gridPane.add(sizeField, 1, 6);
        }
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        saveButton.setOnAction(ae -> {
            try {
                if (item instanceof Tie) {
                    itemController.changeItem(item, brandField.getText(), colorField.getText(),
                            materialComboBox.getValue(), patternComboBox.getValue(),
                            pricePerDayField.getText(), widthField.getText(), lengthField.getText());
                } else {
                    itemController.changeItem(item, brandField.getText(), colorField.getText(),
                            materialComboBox.getValue(), patternComboBox.getValue(),
                            pricePerDayField.getText(), sizeField.getText(), preeTiedComboBox.getValue());

                }
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
}
