package com.ljuslin.app;

import com.ljuslin.controller.*;
import com.ljuslin.repository.Inventory;
import com.ljuslin.repository.MemberRegistry;
import com.ljuslin.repository.RentalRepository;
import com.ljuslin.service.ItemService;
import com.ljuslin.service.MembershipService;
import com.ljuslin.service.RentalService;
import com.ljuslin.service.RevenueService;
import com.ljuslin.view.ItemView;
import com.ljuslin.view.MemberView;
import com.ljuslin.view.RentalView;
import com.ljuslin.view.RevenueView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application
 */
public class WigellApplication extends Application {
    private MemberView memberView = new MemberView();
    private ItemView itemView = new ItemView();
    private RentalView rentalView = new RentalView();
    private RevenueView revenueView = new RevenueView();

    private MemberRegistry memberRegistry = new MemberRegistry();
    private Inventory inventory = new Inventory();
    private RentalRepository rentalRepo = new RentalRepository();

    private MembershipService membershipService = new MembershipService(memberRegistry, rentalRepo);
    private ItemService itemService = new ItemService(inventory, rentalRepo);
    private RentalService rentalService = new RentalService(rentalRepo, membershipService,
            itemService);
    private RevenueService revenueService = new RevenueService(rentalRepo);

    private MemberController memberController = new MemberController(membershipService, memberView);
    private ItemController itemController = new ItemController(itemService, itemView);
    private RentalController rentalController = new RentalController(rentalService,
            memberController, itemController, rentalView);
    private RevenueController revenueController = new RevenueController(revenueService,
            memberController, itemController, rentalController, revenueView);
    private MainController mainController = new MainController(memberController, itemController, rentalController,
            revenueController);

    public WigellApplication() {
    }

    @Override
    public void start(Stage stage) {
        memberView.setMemberController(memberController);
        memberView.setRentalController(rentalController);
        itemView.setItemController(itemController, rentalController);
        rentalView.setRentalController(rentalController);
        revenueView.setRevenueController(revenueController);
        mainController.start(stage);
    }
}
