package com.ljuslin.view;

import com.ljuslin.controller.ItemController;
import com.ljuslin.controller.MainController;
import com.ljuslin.controller.RentalController;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

public class ItemView extends View implements TabView {
    private ItemController itemController;
    private RentalController rentalController;
    /*// Import som kan behövas:
    import javafx.scene.control.TableCell;
    import javafx.scene.control.TableColumn;
    import javafx.util.Callback;

    // ... (Inuti din getTab() metod eller där du definierar kolumnerna)

    // 1. Skapa din Boolean-kolumn (Antag att din Member-klass har en boolean-metod t.ex. 'getIsPremium()')
    TableColumn<Member, Boolean> booleanColumn = new TableColumn<>("Premium");
    booleanColumn.setCellValueFactory(new PropertyValueFactory<>("isPremium")); // Antag att property-namnet är "isPremium"

    // 2. Skapa den anpassade Cell Factory
    booleanColumn.setCellFactory(new Callback<TableColumn<Member, Boolean>, TableCell<Member, Boolean>>() {
        @Override
        public TableCell<Member, Boolean> call(TableColumn<Member, Boolean> param) {
            return new TableCell<Member, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        // 3. Konvertera Boolean till Ja/Nej (HUVUDLOGIKEN)
                        if (item) {
                            setText("Ja");
                        } else {
                            setText("Nej");
                        }
                    }
                }
            };
        }
    });

    // 4. Lägg till den nya kolumnen i din tabell
    table.getColumns().add(booleanColumn);*/
    private Tab tab;
    private BorderPane pane;
    private VBox vbox;
    private Button newItemButton;
    private Button searchButton;
    private Button changeButton;
    private Button deleteButton;
    private Button newRentalButton;
    private Button rechargeButton;
    private Button exitButton;
    private Region region;
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

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    public void setRentalController(RentalController rentalController) {
        this.rentalController = rentalController;
    }

    public Tab getTab() {
        tab = new Tab("Varor");
        pane = new BorderPane();
        vbox = new VBox();
        newItemButton = new Button("Ny");
        searchButton = new Button("Sök");
        changeButton = new Button("Ändra");
        deleteButton = new Button("Ta bort");
        newRentalButton = new Button("Ny uthyrning");
        rechargeButton = new Button("Ladda om");
        newItemButton.setMaxWidth(Double.MAX_VALUE);
        searchButton.setMaxWidth(Double.MAX_VALUE);
        changeButton.setMaxWidth(Double.MAX_VALUE);
        deleteButton.setMaxWidth(Double.MAX_VALUE);
        newRentalButton.setMaxWidth(Double.MAX_VALUE);
        rechargeButton.setMaxWidth(Double.MAX_VALUE);
        exitButton = new Button("Avsluta");
        region = new Region();

        vbox.getChildren().addAll(newItemButton, searchButton, changeButton, deleteButton,
                newRentalButton, rechargeButton, region, exitButton);
        VBox.setVgrow(region, Priority.ALWAYS);
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
            itemController.newItemView();
            populateTable();
        });
        searchButton.setOnAction(e -> {
            itemController.searchItemView();
        });
        changeButton.setOnAction(e -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                itemController.changeItemView(item);
                populateTable();
            } else {
                showInfoAlert("Välj en medlem att ändra!");
            }
        });
        deleteButton.setOnAction(ae -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                try {
                    itemController.removeItem(item);
                    populateTable();
                } catch (ItemException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showErrorAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en vara att ta bort!");
            }
        });
        newRentalButton.setOnAction(ae -> {
            Item item = table.getSelectionModel().getSelectedItem();
            if (item != null) {
                try {
                    rentalController.newRental(item);
                } catch (ItemException e) {
                    showInfoAlert(e.getMessage());
                } catch (FileException e) {
                    showInfoAlert(e.getMessage());
                } catch (MemberException e) {
                    showInfoAlert(e.getMessage());
                } catch (Exception e) {
                    showErrorAlert(e.getMessage());
                }
            } else {
                showInfoAlert("Välj en vara att hyra ut!");
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
            List<Item> list = itemController.getAllItems();
            ObservableList<Item> observableList = FXCollections.observableList(list);
            table.setItems(observableList);
        } catch (FileException e) {
            showInfoAlert(e.getMessage());
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    public void populateTable(List<Item> items) {
        ObservableList<Item> observableList = FXCollections.observableList(items);
        table.setItems(observableList);

    }
}
