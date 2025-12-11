package com.ljuslin.model;

import java.time.LocalDate;

/**
 * Connects a member and an item to make a rental in the shop, has rental date and return date
 * when rental is ended. Saves total revenue for this rental when rental is ended
 *
 * @author Tina Ljuslin
 */
public class Rental {
    private String rentalID;
    private Member member;
    private Item item;
    private double totalRevenue;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental() {
    }

    public Rental(Member member, Item item, LocalDate rentalDate) {
        this.member = member;
        this.item = item;
        this.rentalDate = rentalDate;
        this.totalRevenue = 0;
        this.rentalID = String.valueOf(System.currentTimeMillis());
    }

    public Member getMember() {
        return member;
    }

    public Item getItem() {
        return item;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

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

    public String getRentalID() {
        return rentalID;
    }

    public void setRentalID(String rentalID) {
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
