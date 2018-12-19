package Races;

import Cars.Car;

import java.util.List;

public class DragRace extends Race{

    public DragRace(int length, String route, int prizePool, List<Car> participants) {
        super(length, route, prizePool, participants);
    }

    @Override
    public int calculatePerformance(int horsepower, int acceleration, int suspension, int durability) {
        return (horsepower / acceleration);
    }
}
