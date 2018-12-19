package callofduty.domain.missions;

public class HuntMission extends AbstractMission {

    public HuntMission(String id, Double rating, Double bounty) {

        super(id, rating * 1.5, bounty * 2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hunt Mission - %s%n", super.getId()));
        sb.append(super.toString());
        return sb.toString();
    }

}
