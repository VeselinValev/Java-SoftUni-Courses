import Cars.Car;
import Cars.PerformanceCar;
import Cars.ShowCar;
import Races.*;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarManager {
    private Map<Integer, Car> allCars;
    private List<Car> registrationOrder;
    private Map<Integer, Race> allRaces;
    private Garage garage;
    private List<Integer> closedRaces;

    public CarManager() {
        this.setAllCars(new LinkedHashMap<>());
        this.setRegistrationOrder(new ArrayList<>());
        this.setAllRaces(new LinkedHashMap<>());
        this.setGarage(new Garage(new LinkedHashMap<>()));
        this.setClosedRaces(new ArrayList<>());
    }

    private Map<Integer, Car> getAllCars() {
        return this.allCars;
    }

    private void setAllCars(Map<Integer, Car> allCars) {
        this.allCars = allCars;
    }

    private List<Car> getRegistrationOrder() {
        return this.registrationOrder;
    }

    private void setRegistrationOrder(List<Car> registrationOrder) {
        this.registrationOrder = registrationOrder;
    }

    private Map<Integer, Race> getAllRaces() {
        return this.allRaces;
    }

    private void setAllRaces(Map<Integer, Race> allRaces) {
        this.allRaces = allRaces;
    }

    public Garage getGarage() {
        return this.garage;
    }

    private void setGarage(Garage garage) {
        this.garage = garage;
    }

    public List<Integer> getClosedRaces() {
        return this.closedRaces;
    }

    private void setClosedRaces(List<Integer> closedRaces) {
        this.closedRaces = closedRaces;
    }

    public void register(int id, String type, String brand, String model,
                         int yearOfProduction, int horsepower, int acceleration, int suspension, int durability) {
        Car newCar = null;
        if (type.equals("Performance")) {
            newCar = new PerformanceCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        } else if (type.equals("Show")) {
            newCar = new ShowCar(brand, model, yearOfProduction, horsepower, acceleration, suspension, durability);
        }
        this.getAllCars().putIfAbsent(id, newCar);
        this.getRegistrationOrder().add(newCar);
    }

    public String check(int id) {
        if (allCars.containsKey(id)) {
            Car currentCar = this.getAllCars().get(id);
            return currentCar.toString();
        }
        return "";
    }

    public void open(int id, String type, int length, String route, int prizePool) {
        if (!this.getClosedRaces().contains(id)) {
            Race newRace = null;
            if (type.equals("Casual")) {
                newRace = new CasualRace(length, route, prizePool, new ArrayList<>());
            } else if (type.equals("Drift")) {
                newRace = new DriftRace(length, route, prizePool, new ArrayList<>());
            } else if (type.equals("Drag")) {
                newRace = new DragRace(length, route, prizePool, new ArrayList<>());
            }
            this.getAllRaces().putIfAbsent(id, newRace);
        }
    }

    public void open(int id, String type, int length, String route, int prizePool, int specialRaceParam) {
        if (!this.getClosedRaces().contains(id)) {
            Race newRace = null;
            if (type.equals("TimeLimit")) {
                newRace = new TimeLimitRace(length, route, prizePool, new ArrayList<>(), specialRaceParam);
            } else if (type.equals("Circuit")) {
                newRace = new CircuitRace(length, route, prizePool, new ArrayList<>(), specialRaceParam);
            }
            this.getAllRaces().putIfAbsent(id, newRace);
        }
    }

    public void participate(int carId, int raceId) {
        if (this.getAllCars().containsKey(carId) && !this.getGarage().getParkedCars().containsKey(carId)) {
            Race currentRace = this.getAllRaces().get(raceId);
            if (currentRace instanceof TimeLimitRace) {
                if (currentRace.getParticipants().isEmpty()) {
                    currentRace.getParticipants().add(this.getAllCars().get(carId));
                }
            } else {
                currentRace.getParticipants().add(this.getAllCars().get(carId));
            }
        }
    }

    public String start(int id) {
        String resultString = "";
        if (!this.getAllRaces().get(id).getParticipants().isEmpty()) {
            if (!this.getClosedRaces().contains(id)) {
                if (this.getAllRaces().containsKey(id)) {
                    this.getClosedRaces().add(id);
                    Race currentRace = this.getAllRaces().get(id);
                    if (currentRace instanceof TimeLimitRace) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(String.format("%s - %s", currentRace.getRoute(), currentRace.getLength()));
                        List<Pair<Integer, Car>> result = calculatePerformance(currentRace);
                        for (Pair<Integer, Car> car : result) {
                            sb.append(String.format("\n%s %s - %s s.", car.getValue().getBrand(), car.getValue().getModel(), car.getKey()));
                            if (car.getKey() <= ((TimeLimitRace) currentRace).getGoldTime()) {
                                sb.append(String.format("\nGold Time, $%s.", currentRace.getPrizePool()));
                            } else if (car.getKey() <= ((TimeLimitRace) currentRace).getGoldTime() + 15) {
                                sb.append(String.format("\nSilver Time, $%s.", (currentRace.getPrizePool() * 50) / 100));
                            } else if (car.getKey() > ((TimeLimitRace) currentRace).getGoldTime() + 15) {
                                sb.append(String.format("\nBronze Time, $%s.", (currentRace.getPrizePool() * 30) / 100));
                            }
                            resultString = sb.toString();
                        }
                    } else {
                        if (currentRace instanceof CircuitRace) {
                            for (int i = 0; i < ((CircuitRace) currentRace).getLaps(); i++) {
                                for (Car car : currentRace.getParticipants()) {
                                    car.decreaseDurability(currentRace.getLength());
                                }
                            }
                        }
                        List<Pair<Integer, Car>> result = calculatePerformance(currentRace);
                        resultString = createOutputString(currentRace, result);
                    }
                }
            }
        } else {
            resultString = "Cannot start the race with zero participants.";
        }
        return resultString;
    }

    private List<Pair<Integer, Car>> calculatePerformance(Race currentRace) {
        for (Car car : currentRace.getParticipants()) {
            int performance = currentRace.calculatePerformance(car.getHorsepower(), car.getAcceleration(), car.getSuspension(), car.getDurability());
            currentRace.getCarsByPerformance().add(new Pair(performance, car));
        }
        currentRace.getParticipants().clear();
        return currentRace.getCarsByPerformance().stream().sorted((x, y) -> {
            int compare = Integer.compare(y.getKey(), x.getKey());
            if (compare == 0) {
                compare = Integer.compare(this.getRegistrationOrder().indexOf(x.getValue()), this.getRegistrationOrder().indexOf(y.getValue()));
            }
            return compare;
        }).collect(Collectors.toList());
    }

    private String createOutputString(Race currentRace, List<Pair<Integer, Car>> result) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        int bonusFirst = 50;
        int length = currentRace.getLength();
        int numberOfAwarded = 3;
        if (currentRace instanceof CircuitRace) {
            bonusFirst = 40;
            length = length * ((CircuitRace) currentRace).getLaps();
            numberOfAwarded = 4;
        }
        sb.append(String.format("%s - %s", currentRace.getRoute(), length));
        for (Pair<Integer, Car> car : result) {
            if (counter++ == numberOfAwarded) {
                break;
            }
            switch (counter) {
                case 1:
                    sb.append(String.format("\n%s. %s %s %sPP - $%s", counter, car.getValue().getBrand(),
                            car.getValue().getModel(), car.getKey(), (currentRace.getPrizePool() * bonusFirst) / 100));
                    break;
                case 2:
                    sb.append(String.format("\n%s. %s %s %sPP - $%s", counter, car.getValue().getBrand(),
                            car.getValue().getModel(), car.getKey(), (currentRace.getPrizePool() * 30) / 100));
                    break;
                case 3:
                    sb.append(String.format("\n%s. %s %s %sPP - $%s", counter, car.getValue().getBrand(),
                            car.getValue().getModel(), car.getKey(), (currentRace.getPrizePool() * 20) / 100));
                    break;
                case 4:
                    sb.append(String.format("\n%s. %s %s %sPP - $%s", counter, car.getValue().getBrand(),
                            car.getValue().getModel(), car.getKey(), (currentRace.getPrizePool() * 10) / 100));
                    break;
            }
        }
        return sb.toString();
    }

    public void park(int id) {
        if (this.getAllCars().containsKey(id)) {
            for (Race race : this.getAllRaces().values()) {
                if (race.getParticipants().contains(getAllCars().get(id))) {
                    return;
                }
                ;
            }
            this.getGarage().getParkedCars().putIfAbsent(id, this.getAllCars().get(id));
        }
    }

    public void unpark(int id) {
        if (this.getGarage().getParkedCars().containsKey(id)) {
            this.getGarage().getParkedCars().remove(id);
        }
    }

    public void tune(int tuneIndex, String addOn) {
        for (Car car : this.getGarage().getParkedCars().values()) {
            car.tuneCar(tuneIndex);
            if (car instanceof ShowCar) {
                ((ShowCar) car).increaseStars(tuneIndex);
            } else {
                ((PerformanceCar) car).getAddOns().add(addOn);
            }
        }
    }
}
