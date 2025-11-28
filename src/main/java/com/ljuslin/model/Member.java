package com.ljuslin.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Member, holds name, id, level and history of a member
 * @author Tina Ljuslin
 */
public class Member {
    private String memberID;
    private String firstName;
    private String lastName;
    private Level memberLevel;
    private static int counter = 1;
    private List<String> history = new ArrayList<>();

    /**
     * Empty constructor
     */
    public Member() {

    }

    /**
     * Constructor, creates a new member
     * @param firstName members first name
     * @param lastName members last name
     * @param memberLevel level of this member
     */
    public Member(String firstName, String lastName, Level memberLevel) {
        this.memberID = lastName + "_" + Integer.toString(counter++);
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberLevel = memberLevel;
    }

    /**
     * Returns members ID
     * @return ID of member
     */
    public String getMemberID() {
        return memberID;
    }

    /**
     * Returns members first name
     * @return members first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets members first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns members last name
     * @return members last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets members last name
     * @param lastName, members ne
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns level of this member
     * @return the level
     */
    public Level getMemberLevel() {
        return memberLevel;
    }

    /**
     * Sets level of this member
     * @param memberLevel new level
     */
    public void setMemberLevel(Level memberLevel) {
        this.memberLevel = memberLevel;
    }

    /**
     * Returns history of this member
     * @return list of history
     */
    public List<String> getHistory() {
        return history;
    }

    /**
     * Adds to history
     * @param toAdd string to add to history
     */
    public void addToHistory(String toAdd) {
        history.add(toAdd);
    }

    /**
     * Overrides toString()
     * @return member as a string
     */
    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() +  ", ID: " + getMemberID() + ", Level: " + this.memberLevel;
    }
}
