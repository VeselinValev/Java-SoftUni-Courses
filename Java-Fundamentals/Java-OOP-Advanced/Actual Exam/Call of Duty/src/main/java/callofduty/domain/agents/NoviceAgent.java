package callofduty.domain.agents;

public class NoviceAgent extends AbstractAgent {
    public NoviceAgent(String id, String name, double rating) {
        super(id, name, rating);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Novice Agent - %s%n", super.getName()));
        sb.append(super.toString());
        return sb.toString();
    }
}
