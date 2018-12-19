package app.models.dtos.views;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CustomerTotalSales {
    @Expose
    private String fullName;

    @Expose
    private Integer boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public CustomerTotalSales() {
    }

    public CustomerTotalSales(String fullName, Integer boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney.divide(BigDecimal.valueOf(1), 2, RoundingMode.HALF_EVEN);
    }
}
