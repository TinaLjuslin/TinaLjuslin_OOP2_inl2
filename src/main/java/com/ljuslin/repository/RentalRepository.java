package com.ljuslin.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ljuslin.exception.FileException;
import com.ljuslin.exception.RentalException;
import com.ljuslin.model.Member;
import com.ljuslin.model.Rental;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Holds all rentals, ongoing and ended
 *
 * @author Tina Ljuslin
 */
public class RentalRepository {
    private final String FILENAME = "rentals.json";
    private ObjectMapper mapper = new ObjectMapper();
    private final File rentalFile = new File(FILENAME);

    public RentalRepository() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        if (!rentalFile.exists()) {
            try {
                mapper.writeValue(rentalFile, new ArrayList<Rental>());
            } catch (IOException e) {
                System.err.println("VARNING: Kunde inte skapa initial hyrfil.");
            }
        }
    }

    private void saveRentals(List<Rental> rentals) throws FileException {
        try {
            mapper.writeValue(rentalFile, rentals);
        } catch (IOException e) {
            throw new FileException("Kunde ej spara hyra till fil");
        }
    }

    public void addRental(Rental rental) throws FileException {
        try {
            List<Rental> rentals = getRentals();
            rentals.add(rental);
            mapper.writeValue(new File(FILENAME), rentals);
        } catch (Exception e) {
            throw new FileException("Den nya hyran kunde ej sparas");
        }
    }

    public void updateRental(Rental rental) throws FileException, RentalException {
        boolean updated = false;
        try {
            List<Rental> rentals = getRentals();
            for (Rental r : rentals) {
                if (r.getRentalID().equals(rental.getRentalID())) {
                    rentals.add(rental);
                    rentals.remove(r);
                    updated = true;
                    break;
                }
            }

            mapper.writeValue(new File(FILENAME), rentals);
        } catch (Exception e) {
            throw new FileException("Den nya hyran kunde ej sparas");
        }
        if (!updated) {
            throw new RentalException("Uthyrning kunde ej uppdateras");
        }
    }

    public List<Rental> getRentals() throws FileException {
        try {
            return new ArrayList<>(Arrays.asList(mapper.readValue(rentalFile,
                    Rental[].class)));

        } catch (Exception e) {
            throw new FileException("Hyrfilen kunde ej läsas");
        }

    }

    public void endRental(Rental rental, LocalDate date, double totalRevenue)
            throws FileException, RentalException {
        boolean exists = false;
        try {
            List<Rental> rentals = getRentals();
            for (Rental r : rentals) {
                if (r.getRentalID() == rental.getRentalID()) {
                    r.setReturnDate(date);
                    r.setTotalRevenue(totalRevenue);
                    exists = true;
                    mapper.writeValue(new File(FILENAME), rentals);
                    break;
                }
            }
        } catch (Exception e) {
            throw new FileException("Den nya hyran kunde ej sparas");

        }
        if (!exists) {
            throw new RentalException("Hyran kunde ej hittas i filen");
        }
    }
}
