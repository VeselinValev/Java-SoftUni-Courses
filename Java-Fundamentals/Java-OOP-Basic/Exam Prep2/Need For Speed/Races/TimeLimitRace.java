package Races;

import Cars.Car;

import java.util.List;

public class TimeLimitRace extends Race{

    private int goldTime;

    public TimeLimitRace(int length, String route, int prizePool, List<Car> participants, int goldTime) {
        super(length, route, prizePool, participants);
        this.setGoldTime(goldTime);
    }

    public int getGoldTime() {
        return this.goldTime;
    }

    private void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    @Override
    public int calculatePerformance(int horsepower, int acceleration, int suspension, int durability) {
        return super.getLength()  * ((horsepower / 100) * acceleration);
    }
}
