package com.ljuslin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Member, holds name, id, level and history of a member
 *
 * @author Tina Ljuslin
 */
public class Member {
    private String memberID;
    private String firstName;
    private String lastName;
    private Level memberLevel;
    private List<String> history = new ArrayList<>();

    public Member() {

    }

    public Member(String firstName, String lastName, Level memberLevel) {
        this.memberID = String.valueOf(System.currentTimeMillis());
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberLevel = memberLevel;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Level getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Level memberLevel) {
        this.memberLevel = memberLevel;
    }

    public List<String> getHistory() {
        return history;
    }

    public void addToHistory(String toAdd) {
        history.add(toAdd);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + ", ID: " + getMemberID() + ", Level: " + this.memberLevel;
    }
}
