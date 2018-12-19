package callofduty.domain.missions;

public class SurveillanceMission extends AbstractMission {

    public SurveillanceMission(String id, Double rating, Double bounty) {

        super(id, rating * 0.25, bounty * 1.5);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Surveillance Mission - %s%n", super.getId()));
        sb.append(super.toString());
        return sb.toString();
    }
}
