package com.ljuslin.view;

import com.ljuslin.controller.MemberController;
import com.ljuslin.controller.RentalController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Level;
import com.ljuslin.model.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class MemberView extends View implements TabView{
    private MemberController memberController;
    private RentalController rentalController;
    private Tab tab;
    private BorderPane pane;
    private VBox vbox;
    private Button newButton;
    private Button searchButton;
    private Button changeButton;
    private Button deleteButton;
    private Button historyButton;
    private Button newRentalButton;
    private Button rechargeButton;
    private Button exitButton;
    private Region region;
    private TableView<Member> table;
    private TableColumn<Member, String> idColumn;
    private TableColumn<Member, String> firstNameColumn;
    private TableColumn<Member, String> lastNameColumn;
    private TableColumn<Member, Level> levelColumn;
    public MemberView(){}

    public Tab getTab() {
        tab = new  Tab("Medlemmar");
        pane = new BorderPane();
        vbox = new VBox();
        newButton = new Button("Ny medlem");
        searchButton = new Button("Sök medlem");
        changeButton = new Button("Ändra medlem");
        deleteButton = new Button("Ta bort medlem");
        historyButton = new Button("Visa historia");
        newRentalButton = new Button("Ny uthyrning");
        rechargeButton = new Button("Ladda om");
        exitButton = new Button("Avsluta");
        region = new Region();

        vbox.getChildren().addAll(newButton,searchButton,changeButton,deleteButton, historyButton
                , newRentalButton, rechargeButton, region, exitButton);
        VBox.setVgrow(region, Priority.ALWAYS );
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
        newButton.setOnAction(ae -> {
            memberController.newMemberView();
            populateTable();
        });
        searchButton.setOnAction(ae -> {
            memberController.searchMemberView();

        });
        changeButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                memberController.changeMemberView(member);
                populateTable();
            } else {
                showInfoAlert("Välj en medlem att ändra!");
            }
        });
        newRentalButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                try {
                    rentalController.newRental(member);
                } catch (MemberException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showErrorAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en medlem att hyra ut till!");
            }
        });
        deleteButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                try {
                    memberController.removeMember(member);
                    populateTable();
                } catch (MemberException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showErrorAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en medlem att ta bort!");
            }
        });
        historyButton.setOnAction(ae -> {
            Member member = table.getSelectionModel().getSelectedItem();
            if (member != null) {
                memberController.getHistoryView(member);
            } else {
                showInfoAlert("Välj en medlem att ta bort!");
            }
        });
        rechargeButton.setOnAction(ae -> {
            populateTable();
        });
        exitButton.setOnAction(ae -> {
            System.exit(0);
        });
        return tab;
    }

    private void populateTable() {
        try {
            List<Member> list = memberController.getAllMembers();
            ObservableList<Member> observableList = FXCollections.observableList(list);
            table.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }
    public void populateTable(List<Member> members) {
        ObservableList<Member> observableList = FXCollections.observableList(members);
        table.setItems(observableList);
    }

    public void setMemberController(MemberController memberController) {
        this.memberController = memberController;
    }

    public void setRentalController(RentalController rentalController) {
        this.rentalController = rentalController;
    }
}
