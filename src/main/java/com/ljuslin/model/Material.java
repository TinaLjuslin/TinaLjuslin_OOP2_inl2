package com.ljuslin.model;

/**
 * Enum, material
 * @author Tina Ljuslin
 */
public enum Material {
    POLYESTER ("Polyester"),
    WOOL ("Ull"),
    COTTON ("Bomull"),
    SILK ("Silke");
    private final String swedishName;
    Material(final String swedishName) {
        this.swedishName = swedishName;
    }
    public String getSwedishName() {
        return this.swedishName;
    }
    /**
     * Overrides toString()
     * @return material in lowercase
     */
    @Override
    public String toString() {
        return this.getSwedishName().toLowerCase();
    }
}
