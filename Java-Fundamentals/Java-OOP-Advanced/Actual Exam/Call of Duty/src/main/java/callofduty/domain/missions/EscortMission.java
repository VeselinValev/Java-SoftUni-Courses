package callofduty.domain.missions;

public class EscortMission extends AbstractMission {

    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating * 0.75, bounty * 1.25);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Escort Mission - %s%n", super.getId()));
        sb.append(super.toString());
        return sb.toString();
    }
}
