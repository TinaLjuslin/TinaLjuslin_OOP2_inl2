package com.ljuslin.controller;

import com.ljuslin.model.Member;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HistoryView {
    private MainController mainController;

    private Stage historyStage;
    private Button okButton;
    private ListView<String> historyView;
    public HistoryView(MainController mainController) {
        this.mainController = mainController;
    }
    public void showPopUp(Stage mainStage, Member member) {
        historyStage = new Stage();
        okButton = new Button("Ok");
        historyView = new ListView(FXCollections.observableArrayList(member.getHistory()));
        VBox vBox = new VBox();
        vBox.getChildren().addAll(historyView, okButton);
        Scene scene2 = new Scene(vBox, 600, 300);
        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene2.getStylesheets().add(css);
        okButton.setOnAction(ae -> {
            historyStage.close();
        });
        historyStage.initOwner(mainStage);
        historyStage.initModality(Modality.APPLICATION_MODAL);
        historyStage.setScene(scene2);
        historyStage.showAndWait();

    }
}
