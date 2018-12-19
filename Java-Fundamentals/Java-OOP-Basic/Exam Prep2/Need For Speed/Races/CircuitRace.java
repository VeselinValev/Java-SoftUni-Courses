package Races;

import Cars.Car;

import java.util.List;

public class CircuitRace extends Race{
    private int laps;

    public CircuitRace(int length, String route, int prizePool, List<Car> participants, int laps) {
        super(length, route, prizePool, participants);
        this.setLaps(laps);
    }

    public int getLaps() {
        return this.laps;
    }

    private void setLaps(int laps) {
        this.laps = laps;
    }

    @Override
    public int calculatePerformance(int horsepower, int acceleration, int suspension, int durability) {
        return (horsepower / acceleration) + (suspension + durability);
    }
}
