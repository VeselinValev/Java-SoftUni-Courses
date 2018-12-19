package app.models.dto.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DetailsGameView implements Serializable {

    private String title;

    private BigDecimal price;

    private String description;

    private Date releaseDate;

    public DetailsGameView(String title, BigDecimal price, String description, Date releaseDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public DetailsGameView() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" + "Price: " + this.price + "\n" +
                "Description " + this.description + "\n" + "Release date: " + String.valueOf(this.releaseDate).substring(0, 10);
    }
}
