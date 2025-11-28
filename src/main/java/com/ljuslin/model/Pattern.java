package com.ljuslin.model;

public enum Pattern {
    PAISLEY ("Paisley"),
    STRIPED ("Randig"),
    DOTTED ("Prickig"),
    NO_PATTERN ("Omönstrad"),
    OTHER ("Övrigt");
    private final String swedishName;
    Pattern(String swedishName) {
        this.swedishName = swedishName;
    }
    public String getSwedishName() {
        return swedishName;
    }
    /**
     * Overrides toString()
     * @return pattern in lowercase
     */
    @Override
    public String toString() {
        return this.getSwedishName().toLowerCase();
    }
}
