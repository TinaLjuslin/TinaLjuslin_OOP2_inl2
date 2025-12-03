package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.exception.MemberException;
import com.ljuslin.exception.RentalException;
import com.ljuslin.model.*;
import com.ljuslin.pricing.PremiumPricing;
import com.ljuslin.pricing.StandardPricing;
import com.ljuslin.pricing.StudentPricing;
import com.ljuslin.repository.RentalRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles rentals
 *
 * @autor Tina Ljuslin
 */
public class RentalService {
    private RentalRepository rentalRepo;
    private MembershipService membershipService;
    private ItemService itemService;

    public RentalService() {
    }

    public RentalService(RentalRepository rentalRepo, MembershipService membershipService,
                         ItemService itemService) {
        this.rentalRepo = rentalRepo;
        this.membershipService = membershipService;
        this.itemService = itemService;
    }

    public void newRental(Member member, Item item) throws FileException, MemberException,
            ItemException {
        if (member == null) {
            throw new MemberException("Ingen medlem vald.");
        }
        if (item == null) {
            throw new ItemException("Ingen vara vald.");
        }
        if (!item.isAvailable()) {
            throw new ItemException("Varan är inte tillgänglig.");
        }
        LocalDate date = LocalDate.now();
        Rental rental = new Rental(member, item, date);
        rentalRepo.addRental(rental);
        itemService.changeItemAvailable(item, false);
        membershipService.addToHistory(member, "Rental STARTED: " + rental.toString());
    }

    public void endRental(Rental rental) throws FileException, RentalException, ItemException,
            MemberException {
        List<Rental> rentals = getRentals();
        boolean changed = false;
        for (Rental r : rentals) {
            if (r.getRentalID() == rental.getRentalID()) {
                if (r.getReturnDate() != null) {
                    throw new RentalException("Denna uthyrning är redan avslutad.");
                }
                rental.setReturnDate(LocalDate.now());
                int days = (int) ChronoUnit.DAYS.between(rental.getRentalDate(), rental.getReturnDate());
                double totalPrice = getTotalPrice(rental.getMember(), rental.getItem(), days);
                rental.setTotalRevenue(totalPrice);
                rentalRepo.updateRental(rental);
                itemService.changeItemAvailable(rental.getItem(), true);
                membershipService.addToHistory(rental.getMember(), "Rental ended: " + rental.toString());
                changed = true;
                break;
            }
        }
        if (!changed) {
            throw new RentalException("Ingen pågående uthyrning hittades");
        }
    }
/// //////////////////////////////////////////////////history på rental ended
    private double getTotalPrice(Member member, Item item, int days) {
        switch (member.getMemberLevel()) {
            case PREMIUM:
                return (new PremiumPricing()).getTotalPrice(item.getPricePerDay(), days);
            case STUDENT:
                return (new StudentPricing()).getTotalPrice(item.getPricePerDay(), days);
            default:
                return (new StandardPricing()).getTotalPrice(item.getPricePerDay(), days);
        }
    }

    public List<Rental> getOngoingRentals() throws FileException, RentalException {
        List<Rental> openRentals = new ArrayList<>();
        for (Rental rental : rentalRepo.getRentals()) {
            if (rental.getReturnDate() == null) {
                openRentals.add(rental);
            }
        }
        return openRentals;
    }

    public List<Rental> getEndedRentals() throws FileException, RentalException {
        List<Rental> endedRentals = new ArrayList<>();
        for (Rental rental : rentalRepo.getRentals()) {
            if (rental.getReturnDate() != null) {
                endedRentals.add(rental);
            }
        }
        return endedRentals;
    }

    public List<Rental> getRentals() throws FileException {
        return rentalRepo.getRentals();
    }

    public List<Rental> searchRentals(String string) throws FileException, RentalException {
        List<Rental> searchRentals = new ArrayList<>();
        boolean found = false;
        for (Rental rental : rentalRepo.getRentals()) {
            if (rental.toString().toLowerCase().contains(string)) {
                searchRentals.add(rental);
                found = true;
            }
        }
        if (!found) {
            throw new RentalException("Ingen uthyrning funnen.");
        }
        return searchRentals;
    }
}
