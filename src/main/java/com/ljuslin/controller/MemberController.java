package com.ljuslin.controller;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Level;
import com.ljuslin.model.Member;
import com.ljuslin.service.MembershipService;
import com.ljuslin.view.*;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

import java.util.List;
/**
 * Controlls all Views for Members and calls the right service to perform operations
 *
 * @author Tina Ljuslin
 */
public class MemberController {
    private MembershipService membershipService;
    private MemberView memberView;
    private NewMemberView newMemberView;
    private SearchMemberView searchMemberView;
    private ChangeMemberView changeMemberView;
    private HistoryView historyView;
    private Stage stage;
    private Scene scene;

    public MemberController() {
    }

    public MemberController(MembershipService membershipService, MemberView memberView) {
        this.membershipService = membershipService;
        this.memberView = memberView;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Tab getTab() {
        return memberView.getTab();
    }

    public void populateTable() {
        try {
            memberView.populateTable(membershipService.getAllMembers());
        } catch (FileException e) {
            memberView.showInfoAlert(e.getMessage());
        }
    }

    public List<Member> getAllMembers() throws FileException {
        return membershipService.getAllMembers();
    }

    public void newMemberView() {
        newMemberView = new NewMemberView(this);
        newMemberView.showPopUp(stage, scene);
    }

    public void searchMemberView() {
        searchMemberView = new SearchMemberView(this);
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
        changeMemberView = new ChangeMemberView(this);
        changeMemberView.showPopUp(stage, member);
    }

    public void changeMember(Member member) throws FileException, MemberException {
        membershipService.changeMember(member);
    }

    public void removeMember(Member member) throws FileException, MemberException {
        membershipService.removeMember(member);
    }

    public void getHistoryView(Member member) {
        historyView = new HistoryView();
        historyView.showPopUp(stage, member);
    }
}
