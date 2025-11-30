package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.*;
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
 *
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

    public MainController() {
    }

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

    public void start(Stage stage) {
        this.stage = stage;
        TabPane tabPane = new TabPane();
        scene = new Scene(tabPane, 800, 600);
        Tab memberTab = memberView.getTab();
        memberTab.setClosable(false);
        Tab itemTab = itemView.getTab();
//        Tab rentalTab = rentalView.getTab();
//        Tab revenueTab = revenueView.getTab();

        String css = getClass().getResource("/greenStyles.css").toExternalForm();
        scene.getStylesheets().add(css);
        tabPane.getTabs().addAll(memberTab, itemTab);//, rentalTab, revenueTab);
        stage.setScene(scene);
        stage.show();
    }

    public List<Member> getAllMembers() throws FileException {
        return membershipService.getAllMembers();
    }

    public void newMemberView() {
        NewMemberView newMemberView = new NewMemberView(this);
        newMemberView.showPopUp(stage, scene);
    }

    public void searchMemberView() {
        SearchMemberView searchMemberView = new SearchMemberView(this);
        searchMemberView.showPopUp(stage, scene);
    }

    public void searchMember(String search) throws MemberException,
            FileException {
        List<Member> searchMembers = membershipService.searchMembers(search);
        memberView.populateTable(searchMembers);
    }

    public void newMember(String firstName, String lastName, Level level) throws MemberException,
            FileException {
        membershipService.newMember(firstName, lastName, level);
    }

    public void changeMemberView(Member member) {
        ChangeMemberView changeMemberView = new ChangeMemberView(this);
        changeMemberView.showPopUp(stage, scene, member);
    }

    public void changeMember(Member member) throws FileException, MemberException {
        membershipService.changeMember(member);
    }

    public void removeMember(Member member) throws FileException, MemberException {
        membershipService.removeMember(member);
    }

    public void getHistoryView(Member member) {
        HistoryView historyView = new HistoryView(this);
        historyView.showPopUp(stage, member);
    }

    public List<Item> getAllItems() throws FileException {
        return itemService.getItems();
    }

    public void newItemView() {
        NewItemView newTieView = new NewItemView(this);
        newTieView.showPopUp(stage, scene);
    }

    public void newTie(String brand, String color, Material material, Pattern pattern,
                       String pricePerDay, String width, String length)
            throws FileException, ItemException {
        itemService.newTie(pattern, material, brand, color, pricePerDay, length, width);
    }

    public void newBowtie(String brand, String color, Material material, Pattern pattern,
                          String pricePerDay, String size, boolean preTied)
            throws FileException, ItemException {
        itemService.newBowtie(pattern, material, brand, color, pricePerDay, size, preTied);
    }
    public void removeItem(Item item) throws FileException, ItemException {
        itemService.removeItem(item);
    }
    public void changeItemView(Item item) {
        ChangeItemView changeItemView = new ChangeItemView(this);
        changeItemView.showPopUp(stage, scene, item);

    }
    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String sWidth, String sLength ) throws FileException, ItemException {
        itemService.changeItem(item, brand, color, material, pattern,
                sPricePerDay, sWidth, sLength);
    }
    public void changeItem(Item item, String brand, String color, Material material,
                           Pattern pattern,
                           String sPricePerDay, String sSize, boolean preTied ) throws FileException,
            ItemException {
        itemService.changeItem(item, brand, color, material, pattern, sPricePerDay, sSize, preTied);
    }
}
