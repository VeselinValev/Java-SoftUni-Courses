package app.models.dto.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class AllGamesView implements Serializable {

    private String title;

    private BigDecimal price;

    public AllGamesView() {
    }

    public AllGamesView(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
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
}
