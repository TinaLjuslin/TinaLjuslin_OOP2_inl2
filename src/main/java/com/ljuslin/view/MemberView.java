package com.ljuslin.view;

import com.ljuslin.controller.MainController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Level;
import com.ljuslin.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Lägg till knapar på vänster sida, tabell med befintliga members i mitten
 * knappar: lägg till ny, ändra, ta bort, läs in igen
 * lägg till ett nytt fönster för att lägga till eller ändra, vid ändra markera member först
 * annars exception
 */
public class MemberView extends View implements TabView{
    private MainController mainController;

    private Tab tab;
    private BorderPane pane;
    private VBox vbox;
    private Button newButton;
    private Button searchButton;
    private Button changeButton;
    private Button deleteButton;
    private Button historyButton;
    private Button rechargeButton;
    private TableView<Member> table;
    private TableColumn<Member, String> idColumn;
    private TableColumn<Member, String> firstNameColumn;
    private TableColumn<Member, String> lastNameColumn;
    private TableColumn<Member, Level> levelColumn;

    public MemberView(){}

    public Tab getTab() {
        tab = new  Tab("Member");
        pane = new BorderPane();
        vbox = new VBox();
        newButton = new Button("Ny medlem");
        searchButton = new Button("Sök medlem");
        changeButton = new Button("Ändra medlem");
        deleteButton = new Button("Ta bort medlem");
        historyButton = new Button("Visa historia");
        rechargeButton = new Button("Ladda om");
        newButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        changeButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        historyButton.setMaxWidth(Double.MAX_VALUE);
        rechargeButton.setMaxWidth(Double.MAX_VALUE);
        vbox.getChildren().addAll(newButton,searchButton,changeButton,deleteButton, historyButton
                , rechargeButton);
        table = new TableView<>();
        table.setEditable(false);
        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        firstNameColumn = new TableColumn<>("Förnamn");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn = new TableColumn<>("Efternamn");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("memberLevel"));
        table.getColumns().addAll(idColumn,firstNameColumn,lastNameColumn,levelColumn);
        populateTable();
        pane.setLeft(vbox);
        pane.setCenter(table);
        tab.setContent(pane);
        newButton.setOnAction(e -> {
            mainController.newMemberView();
            populateTable();
        });
        searchButton.setOnAction(e -> {
            mainController.searchMemberView();

        });
        changeButton.setOnAction(e -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                mainController.changeMemberView(member);
                populateTable();
            } else {
                showInfoAlert("Välj en medlem att ändra!");
            }
        });
        deleteButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                try {
                    mainController.removeMember(member);
                    populateTable();
                } catch (MemberException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showInfoAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en medlem att ta bort!");
            }
        });
        historyButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                mainController.getHistoryView(member);
            } else {
                showInfoAlert("Välj en medlem att ta bort!");
            }
        });
        rechargeButton.setOnAction(ae -> {
            populateTable();
        });

        return tab;
    }

    public void setController(MainController mainController) {
        this.mainController = mainController;
    }

    private void populateTable() {
        try {
            List<Member> list = mainController.getAllMembers();
            ObservableList<Member> observableList = FXCollections.observableList(list);
            table.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
    public void populateTable(List<Member> members) {
        try {
            ObservableList<Member> observableList = FXCollections.observableList(members);
            table.setItems(observableList);
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

}
