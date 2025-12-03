package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
import com.ljuslin.exception.RentalException;
import com.ljuslin.exception.RevenueException;
import com.ljuslin.model.Item;
import com.ljuslin.model.Rental;
import com.ljuslin.repository.RentalRepository;

import java.util.List;
/**
 * Handles revenues
 * @author Tina Ljuslin
 */
public class RevenueService {
    private RentalRepository rentalRepo;
    private ItemService itemService;

    public RevenueService() {}

    public RevenueService(RentalRepository rentalRepo, ItemService itemService) {
        this.rentalRepo = rentalRepo;
        this.itemService = itemService;
    }

    public double getTotalRevenue() throws FileException, RevenueException {
        List<Rental> rentals = rentalRepo.getRentals();
        double totalRevenue = 0;
        for (Rental rental : rentals) {
            if (rental.getReturnDate() != null) {
                totalRevenue += rental.getTotalRevenue();
            }
        }
        if (totalRevenue == 0) {
            throw new RevenueException("Ingen vinst");
        }
        return totalRevenue;
    }

    public String getRevenuePerItem(Item item) throws FileException, RevenueException {

        List<Rental> rentals = rentalRepo.getRentals();
        double totalRevenue = 0;
        for (Rental rental : rentals) {
            if (rental.getReturnDate() != null
                    && rental.getItem().equals(item)) {
                totalRevenue += rental.getTotalRevenue();
            }
        }
        if (totalRevenue == 0) {
            throw new RevenueException("Ingen vinst för denna vara");
        }
        return String.valueOf(totalRevenue);
    }
}
