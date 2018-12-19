package app.models.dtos.views.wrappers;

import app.models.dtos.views.CustomerBirthDateYoungDriver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerBirthDateWrapper {

    @XmlElement(name = "customer")
    private List<CustomerBirthDateYoungDriver> customers;

    public CustomerBirthDateWrapper() {
        this.customers = new ArrayList<>();
    }

    public List<CustomerBirthDateYoungDriver> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerBirthDateYoungDriver> customers) {
        this.customers = customers;
    }
}
