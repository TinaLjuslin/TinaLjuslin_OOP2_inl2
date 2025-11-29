package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Level;
import com.ljuslin.model.Member;
import com.ljuslin.service.ItemService;
import com.ljuslin.service.MembershipService;
import com.ljuslin.service.RentalService;
import com.ljuslin.service.RevenueService;
import com.ljuslin.view.*;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * Hadles main menu and users interaction
 * @author Tina Ljuslin
 */
public class MainController {
    private ItemService itemService;
    private MembershipService membershipService;
    private RentalService rentalService;
    private RevenueService revenueService;
    private ItemView itemView;
    private MemberView memberView;
    private RentalView rentalView;
    private RevenueView revenueView;

    private Stage stage;
    private Scene scene;
    public MainController() {}
    public MainController(ItemService itemService, MembershipService membershipService,
                          RentalService rentalService, RevenueService revenueService,
                          ItemView itemView, MemberView memberView,
                          RentalView rentalView, RevenueView revenueView) {
        this.itemService = itemService;
        this.membershipService = membershipService;
        this.rentalService = rentalService;
        this.revenueService = revenueService;
        this.itemView = itemView;
        this.memberView = memberView;
        this.rentalView = rentalView;
        this.revenueView = revenueView;
    }
    /**
     * Starts rental shop by printing first menu
     */
    public void start(Stage stage) {
        this.stage = stage;
        TabPane tabPane = new TabPane();
        scene = new Scene(tabPane, 800, 600);
        Tab memberTab = memberView.getTab();
        memberTab.setClosable(false);
//        Tab itemTab = itemView.getTab();
//        Tab rentalTab = rentalView.getTab();
//        Tab revenueTab = revenueView.getTab();

        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene.getStylesheets().add(css);
        tabPane.getTabs().addAll(memberTab);//, itemTab, rentalTab, revenueTab);
        stage.setScene(scene);
        stage.show();
    }
    public List<Member> getAllMembers() throws FileException {
        try {
            return membershipService.getAllMembers();
        } catch (FileException e) {
            throw e;
        }
    }
    public void newMember() {
        NewMemberView newMemberView = new NewMemberView(this);
        newMemberView.showPopUp(stage, scene);
    }
    public void newMember(String firstName, String lastName, Level level) throws MemberException,
            FileException {
        try {
            membershipService.newMember(firstName, lastName, level);
        } catch (MemberException e) {
            throw e;
        } catch (FileException e) {
            throw e;
        }
    }
    public void changeMemberView(Member member) {
        ChangeMemberView changeMemberView = new ChangeMemberView(this);
        changeMemberView.showPopUp(stage, scene, member);


    }
    public void changeMember(Member member) throws FileException, MemberException {
        try {
            membershipService.changeMember(member);
        } catch (MemberException e) {
            throw e;
        } catch (FileException e) {
            throw e;
        }
    }

}
