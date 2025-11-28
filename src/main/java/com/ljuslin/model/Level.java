package com.ljuslin.model;

/**
 * Enum for Level
 * @author Tina Ljuslin
 */
public enum Level {
    STANDARD ("Standard"),
    STUDENT ("Student"),
    PREMIUM ("Premium");
    private final String swedishName;
    Level(String swedishName) {
        this.swedishName = swedishName;
    }
    public String getSwedishName() {
        return swedishName;
    }

    /**
     * Overrides toString()
     * @return Level in lowercase and capital first letter
     */
    @Override
    public String toString() {
        return this.getSwedishName().toLowerCase();
    }
}
