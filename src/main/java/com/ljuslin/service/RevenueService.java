package com.ljuslin.service;

import com.ljuslin.exception.FileException;
import com.ljuslin.exception.ItemException;
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

    /**
     * Empty constructor
     */
    public RevenueService() {}

    /**
     * Constructor, saves rental repo of this shop
     * @param rentalRepo rental repo to save
     */
    public RevenueService(RentalRepository rentalRepo, ItemService itemService) {
        this.rentalRepo = rentalRepo;
        this.itemService = itemService;
    }

    /**
     * Returns total revenue
     */
    public double totalRevenue() {
        List<Rental> rentals = rentalRepo.getRentals();
        double totalRevenue = 0;
        for (Rental rental : rentals) {
            if (rental.getReturnDate() != null) {
                totalRevenue += rental.getTotalRevenue();
            }
        }
        return totalRevenue;
    }

    /**
     * Returns total revenue for one item
     * @param itemID id of item
     * @return total revenue for this item,
     */
    public String revenuePerItem(String itemID) throws FileException, ItemException {
        Item item;
        try {
            item = itemService.getItem(itemID);
        /*} catch (ItemException e) {
            throw e;*/
        } catch (FileException e) {
            throw e;
        }
        if (item == null) {
            return "Item does not exist";
        }
        List<Rental> rentals = rentalRepo.getRentals();
        double totalRevenue = 0;
        for (Rental rental : rentals) {
            if (rental.getReturnDate() != null
                    && rental.getItem().equals(item)) {
                totalRevenue += rental.getTotalRevenue();
            }
        }
        return String.valueOf(totalRevenue);
    }
}
