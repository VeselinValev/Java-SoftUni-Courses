package app.models.dtos.views;

import app.models.entities.Part;
import com.google.gson.annotations.Expose;

import java.util.HashSet;
import java.util.Set;

public class LocalSupplier {
    @Expose
    private Long id;

    @Expose
    private String name;

    @Expose
    private Integer partsCount;

    public LocalSupplier() {

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

    public Integer getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Integer partsCount) {
        this.partsCount = partsCount;
    }
}
