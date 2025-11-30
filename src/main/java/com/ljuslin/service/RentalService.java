package com.ljuslin.service;

import com.ljuslin.model.*;
import com.ljuslin.pricing.PremiumPricing;
import com.ljuslin.pricing.StandardPricing;
import com.ljuslin.pricing.StudentPricing;
import com.ljuslin.repository.RentalRepository;
import com.ljuslin.utils.Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Handles rentals
 * @autor Tina Ljuslin
 */
public class RentalService {
    private RentalRepository rentalRepo;
    private MembershipService membershipService;
    private ItemService itemService;

    /**
     * Empty constructor
     */
    public RentalService() {}

    /**
     * Constructor
     * @param rentalRepo rental repo of this store
     * @param membershipService, member service of this store
     * @param itemService item service or this store
     */
    public RentalService(RentalRepository rentalRepo, MembershipService membershipService,
                         ItemService itemService) {
        this.rentalRepo = rentalRepo;
        this.membershipService = membershipService;
        this.itemService = itemService;
    }

    /**
     * Creates a new rental
     * @param memberID, member id of member who rents
     * @param itemID the id of the item member rents
     * @param rentalDate the dato of the rent
     * @return string for user prompt
     */
    public String newRental(String memberID, String itemID, String rentalDate) {
        /*Member member = membershipService.getMember(memberID);
        if (member == null) {
            return "Invalid member id: " + memberID;
        }
        Item item = itemService.getItem(itemID);
        if (item == null) {
            return "Invalid item id: " + itemID;
        }

        LocalDate date = Util.checkDate(rentalDate);
        if (date == null) {
            return "Invalid rental date: " + rentalDate;
        }
        Rental rental = new Rental(member, item, date);
        //kolla så item inte är uthyrd
        rentalRepo.addRental(rental);
        //itemService.removeItem(item.getItemID());
        */return null;//"Rental added for " + member.getFirstName() + " " + member.getLastName();
    }

    /**
     * Creates a new rental
     * @param memberID member id of rental
     * @param itemID t´´item id of rental
     * @return string for user prompt
     */
    public String newRental(String memberID, String itemID) {
        /*Member member = membershipService.getMember(memberID);
        if (member == null) {
            return "Invalid member id: " + memberID;
        }
        Item item = itemService.getItem(itemID);
        if (item == null) {
            return "Invalid item id: " + itemID;
        }
        if (!item.isAvailable()) {
            return "Item not available for rent";
        }
        LocalDate date = LocalDate.now();
        Rental rental = new Rental(member, item, date);
        rentalRepo.addRental(rental);
        itemService.changeItemAvailable(item, false);
        membershipService.addToHistory(member, "Rental STARTED: " + rental.toString());
        */return null; //"Rental added for " + member.getFirstName() + " " + member.getLastName();
    }

    /**
     * Ends a rental and saves to members history
     * @param memberID id of memer
     * @param itemID id of item
     * @param returnDate return date as string (from user prompt)
     * @return string for user prompt
     */
    public String endRental(String memberID, String itemID, String returnDate) {
        /*Member member = membershipService.getMember(memberID);
        if (member == null) {
            return "Invalid member id: " + memberID;
        }
        LocalDate date = Util.checkDate(returnDate);
        if (date == null) {
            return "Invalid return date: " + returnDate;
        }
        List<Rental> openRentalsForCustomer = new ArrayList<>();
        for (Rental rental : rentalRepo.getRentals()) {
            if (rental.getMember().getMemberID().equals(memberID) && rental.getReturnDate() == null) {
                openRentalsForCustomer.add(rental);
            }
        }
        if (openRentalsForCustomer.isEmpty()) {
            return "No open rentals for customer: " + member;
        }
        for (Rental rental : openRentalsForCustomer) {
            Item item = rental.getItem();
            if (item.getItemID().equals(itemID)) {
                if (!Util.checkBefore(rental.getRentalDate(), date)) {
                    return "Return date can not be before rental date";
                }
                double totalRevenue = 0;
                int days = Util.daysBetween(rental.getRentalDate(), date);
                totalRevenue = getTotalPrice(member, item, days);
                totalRevenue = Math.round(totalRevenue * 100) / 100;
                rentalRepo.endRental(rental, date, totalRevenue);
                itemService.changeItemAvailable(item, true);
                membershipService.addToHistory(member, ("Rental ENDED: " + rental.toString()));
                return "Rental ended for " + member.getFirstName() + " " +
                 member.getLastName() + ", total cost: " + totalRevenue;
            }
        }
        */return null;// "Item not found";
    }

    /**
     * Returns price per day depending on members level
     * @param member the member
     * @param item the item
     * @return price per day depending on members level
     */
    private double getPricePerDay(Member member, Item item) {
        switch (member.getMemberLevel()) {
            case PREMIUM : return (new PremiumPricing()).getPricePerDay(item.getPricePerDay());

            case STUDENT : return (new StudentPricing()).getPricePerDay(item.getPricePerDay());

            default : return (new StandardPricing()).getPricePerDay(item.getPricePerDay());
        }
    }

    /**
     * Returns total price depending on members level
     * @param member the member
     * @param item the item
     * @param days number of days
     * @return the total price
     */
    private double getTotalPrice(Member member, Item item, int days) {
        switch (member.getMemberLevel()) {
            case PREMIUM : return (new PremiumPricing()).getTotalPrice(item.getPricePerDay(), days);
            case STUDENT : return (new StudentPricing()).getTotalPrice(item.getPricePerDay(), days);
            default : return (new StandardPricing()).getTotalPrice(item.getPricePerDay(), days);
        }
    }

    /**
     * Returns list of all ongoing rentals (that do not have return date)
     * @return List of ongoing rentals
     */
    public List<Rental> getOngoingRentals() {
        List<Rental> openRentals = new ArrayList<>();
        for(Rental rental : rentalRepo.getRentals()) {
            if (rental.getReturnDate() ==null) {
                openRentals.add(rental);
            }
        }
        return openRentals;
    }

    /**
     * Returns list of all ended rentals (have a return date)
     * @return list of ended rentals
     */
    public List<Rental> getEndedRentals() {
        List<Rental> endedRentals = new ArrayList<>();
        for(Rental rental : rentalRepo.getRentals()) {
            if (rental.getReturnDate() !=null) {
                endedRentals.add(rental);
            }
        }
        return endedRentals;
    }

    /**
     * Returns list of all rentals, ongoing and ended
     * @return list of all rentals
     */
    public List<Rental> getRentals() {
        return rentalRepo.getRentals();
    }
}
