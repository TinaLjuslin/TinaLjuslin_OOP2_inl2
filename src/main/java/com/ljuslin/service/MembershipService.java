package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.model.Member;
import com.ljuslin.model.Rental;
import com.ljuslin.repository.MemberRegistry;
import com.ljuslin.model.Level;
import com.ljuslin.repository.RentalRepository;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles members int htis shop
 * @author Tina Ljuslin
 */
public class MembershipService {
    private MemberRegistry memberRegistry;
    private RentalRepository rentalRepo;
    /**
     * Empty constructor
     */
    public MembershipService() {}

    /**
     * Constructor
     * @param memberRegistry the registry holding members
     */
    public MembershipService(MemberRegistry memberRegistry, RentalRepository rentalRepo) {
        this.memberRegistry = memberRegistry;
        this.rentalRepo = rentalRepo;
    }

    /**
     * Creates a new member
     * @param firstName new members first name
     * @param lastName new members last name
     * @param level members level as string
     * @return message to show user
     *//*
    public String newMember(String firstName, String lastName, String level) {
        if (firstName == "" || lastName == "") {
            return "Please enter proper names";
        }
        Level lLevel = getLevel(level);
        if ( lLevel == null) {
            return level + " is not a valid level";
        }
        Member member = new Member(firstName, lastName, lLevel);
        try {
            memberRegistry.addMember(member);
        } catch (FileException e) {

        }
        addToHistory(member, "Member added: " + member.toString());
        return "Member " + member.getName() + " added successfully";
    }*/
    public String newMember(String firstName, String lastName, Level level)
            throws MemberException, FileException {
        if (firstName.trim().equals("")) {
            throw new MemberException("Vänligen ange ett förnamn.");
        } else if (lastName.trim().equals("")) {
            throw new MemberException("Vänligen ange ett efternamn.");
        } else if (level == null) {
            throw new MemberException("Vänligen välj en level.");
        }

        Member member = new Member(firstName, lastName, level);
        try {
            memberRegistry.addMember(member);
        } catch (FileException e) {
            throw e;
        }
        addToHistory(member, "Member added: " + member.toString());
        return "Member " + member.getFirstName() + " " + member.getLastName() + " added successfully";
    }
public void addToHistory(Member member, String history) {
    String time = LocalDateTime.now().format(DateTimeFormatter.
            ofPattern("yyyy-MM-dd HH:mm:ss"));
        member.addToHistory(time + " : " + history);
        //////////////////////////////
    //anropa repo och ändra historien i filen
}
    /**
     * Returns the enum level if there is any matching the string
     * @param level level as string
     * @return the Level or null if no Level was found
     */
    private Level getLevel(String level) {
        level = level.toUpperCase();
        try {

            return Level.valueOf(level);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * Returns all members
     * @return list of all members
     */
    public List<Member> getAllMembers() throws FileException {
        try {
            return memberRegistry.getMembers();
        } catch (FileException e) {
            throw e;
        }
    }

    /**
     * Returns a member by members id
     * @param memberID id of member to get
     * @return member or null if no member was found
     */
    public Member getMember(String memberID) throws MemberException, FileException {
        try {
            return memberRegistry.getMember(memberID);
        } catch (MemberException e) {
            throw e;
        } catch (FileException e) {
            throw e;
        }
    }

    /**
     * Returns list of members matching the serach string
     * @param searchString the string to look for
     * @return list of all members matching
     */
    public List<Member> searchMembers(String searchString) {
        List<Member> searchmembers = new ArrayList<>();
        /*for (Member member : memberRegistry.getMembers()) {
            if (member.toString().toLowerCase().contains(searchString.toLowerCase())) {
                searchmembers.add(member);
            }
        }
        */return searchmembers;
    }

    public String changeMember(Member member) throws MemberException, FileException {
        if (member.getFirstName().trim().equals("")) {
            throw new MemberException("Vänligen ange ett förnamn.");
        } else if (member.getLastName().trim().equals("")) {
            throw new MemberException("Vänligen ange ett efternamn.");
        } else if (member.getMemberLevel() == null) {
            throw new MemberException("Vänligen välj en level.");
        }
        try {
            memberRegistry.changeMember(member);
        } catch (FileException e) {
            throw e;
        }
        addToHistory(member, "Member added: " + member.toString());
        return "Member " + member.getFirstName() + " " + member.getLastName() + " added successfully";
    }   /* member = memberRegistry.changeMember(memberID, lastName, lLevel);
            addToHistory(member, ("Members last name changed to: " + lastName));
            addToHistory(member, ("Members level changed to: " + lLevel));

        } else if (level != "" && lastName == "") {
            Level lLevel = getLevel(level);
            if (lLevel == null) {
                return level + " is not a valid level";
            }
            member = memberRegistry.changeMember(memberID, lLevel);
            addToHistory(member, ("Members level changed to: " + lLevel));

        } else {
            member = memberRegistry.changeMember(memberID, lastName);
            addToHistory(member, ("Members last name changed to: " + lastName));
        }
        if (member == null) {
            return "Member " + memberID + " could not be changed";
        }
        return "Member " + memberID + " changed successfully";
    }*/

    /**
     * Removes a member, returns a string for the user
     * @param memberID, the id of member to remove
     * @return string for the user
     */
    public String removeMember(String memberID) {
        /*Member tempMember = memberRegistry.getMember(memberID);
        List<Rental> rentals = rentalRepo.getRentals();
        for (Rental rental : rentals) {
            if (rental.getMember().equals(tempMember) && rental.getReturnDate() == null) {
                return"Member cannot be deleted, he or she has rentals";
            }
        }
        tempMember = memberRegistry.removeMember(memberID);
        if (tempMember == null) {
            return "Member " + memberID + " could not be removed";
        }*/
        return "Member " + memberID + " removed";
    }

    /**
     * Returns history of a member
     * @param memberID members id
     * @return ist of members history
     */
    public List<String> getHistory(String memberID) {
    /*    Member member = memberRegistry.getMember(memberID);
        List<String> history = new ArrayList<>();
        if (member == null) {
            history.add("Member " + memberID + " could not be found");
            return history;
        }
        history = member.getHistory();
        if (history == null || history.size() == 0) {
            history.add("No history for member " +  memberID);
            return history;
        }
    */    return null;
    }
}