package app.models.dtos.views;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleWIthDiscount {
    @Expose
    private CarAndParts car;
    @Expose
    private String customerName;
    @Expose
    private BigDecimal discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SaleWIthDiscount() {
    }

    public CarAndParts getCar() {
        return car;
    }

    public void setCar(CarAndParts car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount.stripTrailingZeros();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
