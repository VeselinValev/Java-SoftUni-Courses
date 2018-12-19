package app.dto;

import app.enums.AgeRestriction;
import app.enums.EditionType;

import java.math.BigDecimal;

public class ReducedBook {
    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public ReducedBook() {
    }

    public String getTitle() {
        return title;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
