package app.models.dtos.views.wrappers;

import app.models.dtos.views.CustomerTotalSales;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerTotalSalesWrapper {

    @XmlElement(name = "customer")
    private List<CustomerTotalSales> customers;

    public CustomerTotalSalesWrapper() {
        this.customers = new ArrayList<>();
    }

    public List<CustomerTotalSales> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerTotalSales> customers) {
        this.customers = customers;
    }
}
