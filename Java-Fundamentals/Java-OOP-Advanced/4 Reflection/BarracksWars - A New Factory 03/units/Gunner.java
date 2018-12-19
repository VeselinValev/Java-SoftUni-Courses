package units;

public class Gunner extends AbstractUnit{
    private static final int GUNNER_HEALTH = 25;
    private static final int GUNNER_DAMAGE = 7;

    protected Gunner() {
        super(GUNNER_HEALTH, GUNNER_DAMAGE);
    }
}
