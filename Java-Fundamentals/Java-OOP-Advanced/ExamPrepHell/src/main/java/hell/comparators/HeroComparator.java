package hell.comparators;

import hell.interfaces.Hero;

import java.util.Comparator;

public class HeroComparator implements Comparator<Hero> {
    @Override
    public int compare(Hero o1, Hero o2) {
        int compare = Long.compare((o2.getAgility() + o2.getIntelligence() + o2.getStrength()),
                (o1.getAgility() + o1.getIntelligence() + o1.getStrength()));
        if (compare == 0){
            compare = Long.compare((o2.getHitPoints() + o2.getDamage()),
                    ((o1.getHitPoints() + o1.getDamage())));
        }
        return compare;
    }
}
