package Races;

import Cars.Car;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;
    private List<Pair<Integer, Car>> carsByPerformance;

    public Race(int length, String route, int prizePool, List<Car> participants) {
        this.setLength(length);
        this.setRoute(route);
        this.setPrizePool(prizePool);
        this.setParticipants(participants);
        this.setCarsByPerformance(new ArrayList<>());
    }

    public abstract int calculatePerformance(int horsepower, int acceleration, int suspension, int durability);

    public List<Pair<Integer, Car>> getCarsByPerformance() {
        return this.carsByPerformance;
    }

    private void setCarsByPerformance(List<Pair<Integer, Car>> carsByPerformance) {
        this.carsByPerformance = carsByPerformance;
    }

    public int getLength() {
        return this.length;
    }

    private void setLength(int length) {
        this.length = length;
    }

    public String getRoute() {
        return this.route;
    }

    private void setRoute(String route) {
        this.route = route;
    }

    public int getPrizePool() {
        return this.prizePool;
    }

    private void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public List<Car> getParticipants() {
        return this.participants;
    }

    private void setParticipants(List<Car> participants) {
        this.participants = participants;
    }
}
