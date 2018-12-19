package app.models.dtos.views.wrappers;

import app.models.dtos.views.CarAndParts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsWrapper {

    @XmlElement(name = "car")
    private List<CarAndParts> cars;

    public CarAndPartsWrapper() {
        this.cars = new ArrayList<>();
    }

    public List<CarAndParts> getCars() {
        return cars;
    }

    public void setCars(List<CarAndParts> cars) {
        this.cars = cars;
    }
}
