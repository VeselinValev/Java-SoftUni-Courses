package app.models.dtos.views.wrappers;

import app.models.dtos.views.LocalSupplier;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierWrapper {

    @XmlElement(name = "supplier")
    private List<LocalSupplier> suppliers;

    public LocalSupplierWrapper() {
        this.suppliers = new ArrayList<>();
    }

    public List<LocalSupplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<LocalSupplier> suppliers) {
        this.suppliers = suppliers;
    }
}
