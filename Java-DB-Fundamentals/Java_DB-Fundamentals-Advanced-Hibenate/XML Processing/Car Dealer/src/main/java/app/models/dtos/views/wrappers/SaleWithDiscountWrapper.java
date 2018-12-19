package app.models.dtos.views.wrappers;

import app.models.dtos.views.SaleWIthDiscount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountWrapper {

    @XmlElement(name = "sale")
    private List<SaleWIthDiscount> sales;

    public SaleWithDiscountWrapper() {
        this.sales = new ArrayList<>();
    }

    public List<SaleWIthDiscount> getSales() {
        return sales;
    }

    public void setSales(List<SaleWIthDiscount> sales) {
        this.sales = sales;
    }
}
