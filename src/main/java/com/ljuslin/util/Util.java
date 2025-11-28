package com.ljuslin.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Static class with static helper methods
 * @author Tina Ljuslin
 */
public abstract class Util {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Waits to get the user choice from the prompt
     * @return int, the menu choice the user made, -1 if incorrect
     */
    public static int getUserChoice() {
        int answer;
        try {
            answer = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
        scanner.nextLine();
        return answer;
    }

    /**
     * Takes a date as a string and tries to make it a LocalDate (yyyy-MM-dd)
     * @param date, the string to convert
     * @return LocalDate, the date as LocalDate or null if not correct date
     */
    public static LocalDate checkDate(String date) {
        try {
            LocalDate properDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return properDate;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Checks if first date is before second date
     * @param firstDate, first date
     * @param secondDate, second date
     * @return boolan, true if first date is before second date
     */
    public static boolean checkBefore(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate);
    }

    /**
     * Counts days between to dates
     * @param firstDate first date
     * @param secondDate second date
     * @return number of days between dates, 0 if first date is after second
     */
    public static int daysBetween(LocalDate firstDate, LocalDate secondDate) {
        int counter = 0;
        if (firstDate.isAfter(secondDate)) {
            return 0;
        }
        while (firstDate.isBefore(secondDate)) {
            counter++;
            firstDate = firstDate.plusDays(1);
        }
        return counter;
    }
}
