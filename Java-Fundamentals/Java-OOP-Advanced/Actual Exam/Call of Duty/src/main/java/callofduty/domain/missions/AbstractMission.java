package callofduty.domain.missions;

import callofduty.interfaces.Mission;

import java.lang.reflect.Field;

public abstract class AbstractMission implements Mission {
    private String id;
    private double rating;
    private double bounty;
    private boolean isCompleted;

    protected AbstractMission(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
        this.isCompleted = false;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field isCompleted = null;
        try {
            isCompleted = this.getClass().getSuperclass().getDeclaredField("isCompleted");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        isCompleted.setAccessible(true);
        String missionStatus = "";
        try {
            if (((boolean)isCompleted.get(this))){
                missionStatus = "Completed";
            }else{
                missionStatus = "Open";
            }
            sb.append(String.format("Status: %s%n", missionStatus));
            sb.append(String.format("Rating: %.2f%n", this.rating));
            sb.append(String.format("Bounty: %.2f", this.bounty));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
