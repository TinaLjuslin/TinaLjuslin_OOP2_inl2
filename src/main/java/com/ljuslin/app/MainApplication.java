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
public class MainApplication extends Application {
    private MemberView memberView = new MemberView();
    private ItemView itemView = new ItemView();
    private RentalView rentalView = new RentalView();
    private RevenueView revenueView = new RevenueView();

    private MemberRegistry memberRegistry = new MemberRegistry();
    private Inventory inventory = new Inventory();
    private RentalRepository rentalRepo = new RentalRepository();

    private MembershipService membershipService = new MembershipService(memberRegistry, rentalRepo);
    private ItemService itemService = new ItemService(inventory);
    private RentalService rentalService = new RentalService(rentalRepo, membershipService,
            itemService);
    private RevenueService revenueService = new RevenueService(rentalRepo, itemService);

    private MainController mainController = new MainController(itemService, membershipService,
            rentalService, revenueService, itemView, memberView, rentalView, revenueView);



    @Override
    public void start(Stage stage) {
        memberView.setController(mainController);
        itemView.setController(mainController);
        rentalView.setController(mainController);
        revenueView.setController(mainController);
        mainController.start(stage);
    }
}
