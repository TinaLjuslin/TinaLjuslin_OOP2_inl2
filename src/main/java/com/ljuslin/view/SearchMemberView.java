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
public class SearchMemberView extends View{
    private MainController mainController;

    private Stage searchMemberStage;
    private TextField searchField;
    private Label searchLabel;
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
        searchLabel = new Label("Sök:");
        searchField = new TextField();
        gridPane = new GridPane();
        gridPane.add(searchLabel, 0, 0);
        gridPane.add(searchField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(cancelButton, 1, 1);
        Scene scene2 = new Scene(gridPane, 300, 300);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);

        searchButton.setOnAction( ae -> {
            try {
                mainController.searchMember(searchField.getText());
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
}
