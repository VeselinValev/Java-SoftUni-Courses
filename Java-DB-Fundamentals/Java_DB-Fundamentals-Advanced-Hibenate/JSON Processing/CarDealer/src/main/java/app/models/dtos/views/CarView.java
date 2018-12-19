package app.models.dtos.views;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CarView {
    @Expose
    private CarAndParts car;

    @Expose
    private List<PartForCar> parts;

    public CarView() {
        this.parts = new ArrayList<>();
    }

    public CarAndParts getCar() {
        return car;
    }

    public void setCar(CarAndParts car) {
        this.car = car;
    }

    public List<PartForCar> getParts() {
        return parts;
    }

    public void setParts(List<PartForCar> parts) {
        this.parts = parts;
    }
}
