package com.ljuslin.repository;

import com.ljuslin.model.Rental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds all rentals, ongoing and ended
 * @author Tina Ljuslin
 */
public class RentalRepository {
    private List<Rental> rentals = new ArrayList<>();

    /**
     * Constructor
     */
    public RentalRepository() {}

    /**
     * Adds a rental
     * @param rental
     */
    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    /**
     * Returns all rentals
     * @return
     */
    public List<Rental> getRentals() {
        return rentals;
    }

    /**
     * Ends rental
     * @param rental
     * @param date
     * @param totalRevenue
     * @return
     */
    public Rental endRental(Rental rental, LocalDate date, double totalRevenue) {
        for (Rental r : rentals) {
            if (r.equals(rental)) {
                r.setReturnDate(date);
                r.setTotalRevenue(totalRevenue);
                return r;
            }
        }
        return null;
    }
}
