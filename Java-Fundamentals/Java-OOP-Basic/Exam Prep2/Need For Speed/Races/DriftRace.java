package Races;

import Cars.Car;

import java.util.List;

public class DriftRace extends Race {

    public DriftRace(int length, String route, int prizePool, List<Car> participants) {
        super(length, route, prizePool, participants);
    }

    @Override
    public int calculatePerformance(int horsepower, int acceleration, int suspension, int durability) {
        return (suspension + durability);
    }
}
