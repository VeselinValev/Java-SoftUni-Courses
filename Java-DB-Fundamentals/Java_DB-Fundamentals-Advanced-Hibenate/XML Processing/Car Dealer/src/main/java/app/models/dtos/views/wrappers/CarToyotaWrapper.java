package app.models.dtos.views.wrappers;

import app.models.dtos.views.CarToyota;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarToyotaWrapper {

    @XmlElement(name = "car")
    private List<CarToyota> cars;

    public CarToyotaWrapper() {
        this.cars = new ArrayList<>();
    }

    public List<CarToyota> getCars() {
        return cars;
    }

    public void setCars(List<CarToyota> cars) {
        this.cars = cars;
    }
}
