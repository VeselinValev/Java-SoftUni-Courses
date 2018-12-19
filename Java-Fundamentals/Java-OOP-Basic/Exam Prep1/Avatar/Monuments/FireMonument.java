package Avatar.Monuments;

public class FireMonument extends Monument {
    private int fireAffinity;

    public FireMonument(String name, int fireAffinity) {
        super(name);
        this.setFireAffinity(fireAffinity);
        super.changePowerBoost(fireAffinity);
    }

    public int getFireAffinity() {
        return fireAffinity;
    }

    private void setFireAffinity(int fireAffinity) {
        this.fireAffinity = fireAffinity;
    }

    @Override
    public String toString() {
        return String.format("###Fire Monument: %s, Fire Affinity: %s%n", super.getName(), this.getFireAffinity());
    }
}
