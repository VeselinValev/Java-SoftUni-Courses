package app.services.impl;

import app.models.dtos.binding.CarDto;
import app.models.dtos.views.CarAndParts;
import app.models.dtos.views.CarToyota;
import app.models.dtos.views.CarView;
import app.models.dtos.views.PartForCar;
import app.models.entities.Car;
import app.models.entities.Part;
import app.repositories.CarRepository;
import app.services.api.CarService;
import app.services.api.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private PartService partService;
    private ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartService partService) {
        this.carRepository = carRepository;
        this.partService = partService;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void saveAll(CarDto[] cars) {
        List<Part> parts = this.partService.getAllParts();
        Random random = new Random();
        for (CarDto carDto : cars) {
            Car car = this.modelMapper.map(carDto, Car.class);
            for (int i = 0; i < random.nextInt(10) + 10; i++){
                car.getParts().add(parts.get(random.nextInt(parts.size())));
            }
            this.carRepository.saveAndFlush(car);
        }
    }

    @Override
    public List<Car> getAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarToyota> getAllToyotas() {

        return this.carRepository.getAllToyotas()
                .stream()
                .map(x -> this.modelMapper.map(x, CarToyota.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarView> getAllCarsAndParts() {
        List<Car> cars = this.carRepository.getAllCars();
        List<CarView> carsDto = new ArrayList<>();
        for (Car car: cars){
            CarView carView = new CarView();
            CarAndParts carAndParts = new CarAndParts();
            for (Part part: car.getParts()){
                PartForCar partForCar = this.modelMapper.map(part, PartForCar.class);
                carView.getParts().add(partForCar);
            }
            carAndParts.setMake(car.getMake());
            carAndParts.setModel(car.getModel());
            carAndParts.setTravelledDistance(car.getTravelledDistance());
            carView.setCar(carAndParts);
            carsDto.add(carView);
        }
        return carsDto;
    }
}
