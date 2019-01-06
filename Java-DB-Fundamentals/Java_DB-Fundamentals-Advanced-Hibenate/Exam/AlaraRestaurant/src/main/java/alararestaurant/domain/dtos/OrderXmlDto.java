package alararestaurant.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXmlDto {

    @XmlElement(name = "customer")
    @NotNull
    private String customer;

    @XmlElement(name = "employee")
    @NotNull
    private String employee;

    @XmlElement(name = "date-time")
    @NotNull
    private String dateTime;

    @XmlElement(name = "type")
    @NotNull
    private String type;

    private ItemXmlRootDto items;

    public OrderXmlDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemXmlRootDto getItems() {
        return items;
    }

    public void setItems(ItemXmlRootDto items) {
        this.items = items;
    }
}
