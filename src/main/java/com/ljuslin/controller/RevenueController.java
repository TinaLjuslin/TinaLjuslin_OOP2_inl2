package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.RevenueException;
import com.ljuslin.model.Item;
import com.ljuslin.service.RevenueService;
import com.ljuslin.view.NewRentalView;
import com.ljuslin.view.RevenueView;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
/**
 * Controlls all Views for Revenue and calls the right service to perform operations
 *
 * @author Tina Ljuslin
 */
public class RevenueController {
    private MemberController memberController;
    private ItemController itemController;
    private RevenueService revenueService;
    private RevenueView revenueView;
    private NewRentalView newRentalView;
    private Stage stage;
    private Scene scene;

    public RevenueController() {
    }

    public RevenueController(RevenueService revenueService, MemberController memberController,
                             ItemController itemController, RevenueView revenueView) {
        this.revenueService = revenueService;
        this.revenueView = revenueView;
        newRentalView = new NewRentalView(memberController, itemController);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Tab getTab() {
        return revenueView.getTab();
    }

    public void populateTable() {
        revenueView.updateTotalRevenue();
    }

    public String getTotalRevenue() throws FileException, RevenueException {
        return revenueService.getTotalRevenue();
    }

    public String getRevenuePerItem(Item item) throws FileException, RevenueException {
        return revenueService.getRevenuePerItem(item);
    }
    public Item getItemForRevenue() {
        return newRentalView.showAllItemPopUp(stage, scene);

    }
}
