package com.ljuslin.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Connects a member and an item to make a rental in the shop, have rental date and return date
 * when rental is ended. Saves total revenue for this rental when rental is ended
 * @author Tina Ljuslin
 */
public class Rental {
    private long rentalID;
    private Member member;
    private Item item;
    private double totalRevenue;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental() {}

    public Rental(Member member, Item item, LocalDate rentalDate) {
        this.member = member;
        this.item = item;
        this.rentalDate = rentalDate;
        this.totalRevenue = 0;
        this.rentalID = System.currentTimeMillis();
    }

    /**
     * returns member of rental
     * @return member of this rental
     */
    public Member getMember() {
        return member;
    }

    /**
     * Returns item of this rental
     * @return item of this rental
     */
    public Item getItem() {
        return item;
    }

    /**
     * returns total revenue of this rental
     * @return total revenue
     */
    public double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Returns date of rental
     * @return date of rental
     */
    public LocalDate getRentalDate() {
        return rentalDate;
    }

    /**
     * Returns date of return of this rental
     * @return date of return
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Sets return date of this rental
     * @param returnDate date of return
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Sets total revenue of this rental
     * @param totalRevenue total revenue
     */
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public long getRentalID() {
        return rentalID;
    }

    public void setRentalID(long rentalID) {
        this.rentalID = rentalID;
    }

    @Override
    public String toString() {
        String s = "Member: ";
        s = s.concat(member.getFirstName()).concat(member.getLastName());
        s = s.concat(", Rented item:\n").concat(item.toString());
        s = s.concat("\nRented date: ").concat(String.valueOf(rentalDate));
        if (returnDate != null) {
            s = s.concat("\nReturn date: ").concat(String.valueOf(returnDate));
            s = s.concat("\nTotal revenue: ").concat(String.format("%.2f", totalRevenue));
        }
        return s;
    }
}
