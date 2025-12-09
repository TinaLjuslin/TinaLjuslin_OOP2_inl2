package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.exception.RentalException;
import com.ljuslin.model.Item;
import com.ljuslin.model.Member;
import com.ljuslin.model.Rental;
import com.ljuslin.service.RentalService;
import com.ljuslin.view.NewRentalView;
import com.ljuslin.view.RentalView;
import com.ljuslin.view.SearchRentalView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.util.List;

public class RentalController {
    private ItemController itemController;
    private MemberController memberController;
    private RentalService rentalService;
    private RentalView rentalView;
    private NewRentalView newRentalView;
    private Stage stage;
    private Scene scene;

    public RentalController() {
    }

    public RentalController(RentalService rentalService,
                            MemberController memberController,
                            ItemController itemController, RentalView rentalView) {
        this.rentalService = rentalService;
        this.rentalView = rentalView;
        this.memberController = memberController;
        this.itemController = itemController;
        newRentalView = new NewRentalView(memberController, itemController);
    }//this,

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Tab getTab() {
        return rentalView.getTab();
    }

    public void populateTable() {
        try {
            rentalView.populateTable(rentalService.getRentals());
        } catch (FileException e) {
            rentalView.showInfoAlert(e.getMessage());
        }
    }

    public List<Rental> getAllRentals() throws FileException, RentalException {
        return rentalService.getRentals();
    }

    public void endRental(Rental rental) throws FileException, RentalException, ItemException,
            MemberException {
        rentalService.endRental(rental);
    }

    public double getRevenuePerRental(Rental rental) throws FileException, RentalException {
        return rentalService.getRevenuePerRental(rental);
    }

    public void newRental() throws FileException, MemberException, ItemException {
        Member member = newRentalView.showMemberPopUp(stage);
        Item item = newRentalView.showAvailableItemPopUp(stage);
        rentalService.newRental(member, item);
    }

    public void newRental(Member member) throws FileException, MemberException, ItemException {
        Item item = newRentalView.showAvailableItemPopUp(stage);
        rentalService.newRental(member, item);
    }

    public void newRental(Item item) throws FileException, MemberException, ItemException {
        Member member = newRentalView.showMemberPopUp(stage);
        rentalService.newRental(member, item);
    }

    public void searchRentalView() {
        SearchRentalView searchRentalView = new SearchRentalView(this);
        searchRentalView.showPopUp(stage, scene);
    }

    public void searchRental(String search) throws RentalException,
            FileException {
        List<Rental> searchRentals = rentalService.searchRentals(search);
        rentalView.populateTable(searchRentals);
    }
}
