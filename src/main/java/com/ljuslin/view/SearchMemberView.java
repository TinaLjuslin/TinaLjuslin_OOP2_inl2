package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Shows a popup where the user can enter values for a new member
 * @author tina.ljuslin@studerande.yh.se
 */
public class SearchMemberView {
    private MainController mainController;

    private Stage searchMemberStage;
    private TextField fistNameField;
    private TextField lastNameField;
    private ComboBox<Level> levelComboBox;
    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label levelLabel;
    private Button searchButton;
    private Button cancelButton;
    private GridPane gridPane;

    public SearchMemberView(MainController mainController) {
        this.mainController = mainController;
    }

    public void showPopUp(Stage mainStage, Scene mainScene) {
        searchMemberStage = new Stage();
        searchButton = new Button("Sök");
        cancelButton = new Button("Avbryt");
        firstNameLabel = new Label("Förnamn");
        lastNameLabel = new Label("Efternamn");
        levelLabel = new Label("Level");
        fistNameField = new TextField();
        lastNameField = new TextField();
        ObservableList<Level> levels = FXCollections.observableArrayList(Level.values());
        levelComboBox = new ComboBox<>(levels);
        //levelComboBox.getSelectionModel().select(0);

        gridPane = new GridPane();
        gridPane.add(firstNameLabel, 0, 0);
        gridPane.add(lastNameLabel, 0, 1);
        gridPane.add(levelLabel, 0, 2);
        gridPane.add(fistNameField, 1, 0);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(levelComboBox, 1, 2);
        gridPane.add(searchButton, 1, 3);
        gridPane.add(cancelButton, 2, 3);
        Scene scene2 = new Scene(gridPane, 300, 300);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);

        searchButton.setOnAction( ae -> {
            try {
                mainController.searchMember(fistNameField.getText(), lastNameField.getText(),
                        levelComboBox.getValue());
                searchMemberStage.close();
            } catch (MemberException e) {
                showInfoAlert(e.getMessage());
            } catch (FileException e) {
                showInfoAlert(e.getMessage());
            } catch (Exception e) {
                showErrorAlert(e.getMessage());
            }


        });
        cancelButton.setOnAction(ae -> {
            searchMemberStage.close();
        });

        searchMemberStage.initOwner(mainStage);
        searchMemberStage.initModality(Modality.APPLICATION_MODAL);
        searchMemberStage.setScene(scene2);
        searchMemberStage.showAndWait();
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
