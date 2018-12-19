package app.services.api;

import app.models.dtos.binding.CarDto;
import app.models.dtos.views.CarAndParts;
import app.models.dtos.views.CarToyota;
import app.models.dtos.views.CarView;
import app.models.entities.Car;

import java.util.List;

public interface CarService {
    void saveAll(CarDto[] cars);

    List<Car> getAllCars();

    List<CarToyota> getAllToyotas();

    List<CarView> getAllCarsAndParts();


}
