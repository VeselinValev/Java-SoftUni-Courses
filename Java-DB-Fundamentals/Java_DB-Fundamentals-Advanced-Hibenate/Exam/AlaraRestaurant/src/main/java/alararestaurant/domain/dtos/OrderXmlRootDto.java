package alararestaurant.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXmlRootDto {

    @XmlElement(name = "order")
    private List<OrderXmlDto> orders;

    public OrderXmlRootDto() {

        this.orders = new ArrayList<>();
    }

    public List<OrderXmlDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderXmlDto> orders) {
        this.orders = orders;
    }
}
