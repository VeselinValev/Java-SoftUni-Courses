package app.models.dtos.binding;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierDto implements Serializable {
    @Expose
    private String name;
    @Expose
    private Boolean isImporter;

    public SupplierDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsImporter() {
        return isImporter;
    }

    public void setIsImporter(Boolean importer) {
        isImporter = importer;
    }
}
