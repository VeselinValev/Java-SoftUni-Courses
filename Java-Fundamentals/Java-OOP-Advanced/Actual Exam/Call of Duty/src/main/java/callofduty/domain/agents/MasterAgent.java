package callofduty.domain.agents;

import callofduty.interfaces.BountyAgent;

public class MasterAgent extends AbstractAgent implements BountyAgent {
    private double bounty;

    public MasterAgent(String id, String name, double rating) {
        super(id, name, rating);
        this.bounty = 0;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Master Agent - %s%n", super.getName()));
        sb.append(super.toString());
        sb.append(String.format("%nBounty Earned: $%.2f", this.getBounty()));
        return sb.toString();
    }
}
