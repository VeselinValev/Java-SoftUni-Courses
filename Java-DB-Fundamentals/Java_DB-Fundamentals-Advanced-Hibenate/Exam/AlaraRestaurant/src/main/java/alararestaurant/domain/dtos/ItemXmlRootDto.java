package alararestaurant.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemXmlRootDto {

    @XmlElement(name = "item")
    private List<ItemXmlDto> items;

    public ItemXmlRootDto() {
        this.items = new ArrayList<>();
    }

    public List<ItemXmlDto> getItems() {
        return items;
    }

    public void setItems(List<ItemXmlDto> items) {
        this.items = items;
    }
}
