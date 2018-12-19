package MooD3;

public class DemonImpl extends CharacterImpl implements Demon{
    private double energy;

    public DemonImpl(String userName, String type, int level, double energy) {
        super(userName, type, level);
        this.setEnergy(energy);
        this.setHashedPassword(userName);
    }

    private void setEnergy(double energy) {
        this.energy = energy;
    }

    @Override
    public void setHashedPassword(String userName) {
        int temp = this.getUserName().length() * 217;
        super.setHashedPassword("" + temp);
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\"%s\" | \"%s\" -> %s", super.getUserName(), super.getHashedPassword(), super.getType()));
        sb.append("\n").append(super.getLevel()*this.getEnergy());
        return sb.toString();
    }
}
