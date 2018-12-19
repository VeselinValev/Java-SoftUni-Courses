package app.models.dtos.views;

import app.models.entities.Sale;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerBirthDateYoungDriver implements Serializable {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private Boolean isYoungerDiver;
    @Expose
    private List<Sale> sales;

    public CustomerBirthDateYoungDriver() {
        this.sales = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungerDiver() {
        return isYoungerDiver;
    }

    public void setYoungerDiver(Boolean youngerDiver) {
        isYoungerDiver = youngerDiver;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
