package weapons;

import gems.Gem;
public abstract class WeaponsImpl implements Weapon, Comparable<Weapon>{
    private String name;
    private int minDmg;
    private int maxDmg;
    private Gem[] gems;
    private int strength;
    private int agility;
    private int vitality;

    public WeaponsImpl(String name) {
        this.setName(name);
    }
    @Override
    public double getLevel() {
        return (double)(this.getMinDmg() + this.getMaxDmg())/ 2 + this.getStrength() + this.getAgility() + this.getVitality();
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getMinDmg() {
        int increment = 0;
        for (Gem gem: this.gems){
            if (gem != null){
                increment += 2 * gem.getStrength() + gem.getAgility();
            }
        }
        return this.minDmg + increment;
    }
    protected void setMinDmg(int minDmg){
        this.minDmg = minDmg;
    };

    public int getMaxDmg() {
        int increment = 0;
        for (Gem gem: this.gems){
            if (gem  != null){
                increment += 3 * gem.getStrength() + 4 * gem.getAgility();
            }
        }
        return this.maxDmg + increment;
    }

    protected void setMaxDmg(int maxDmg){
        this.maxDmg = maxDmg;
    };

    protected void setGems(int numberOfSockets){
        this.gems = new Gem[numberOfSockets];
    };

    public int getStrength() {
        int increment = 0;
        for (Gem gem: this.gems){
            if (gem != null){
                increment += gem.getStrength();
            }
        }
        return this.strength + increment;
    }

    public int getAgility() {
        int increment = 0;
        for (Gem gem: this.gems){
            if (gem != null){
                increment += gem.getAgility();
            }
        }
        return this.agility + increment;
    }

    public int getVitality() {
        int increment = 0;
        for (Gem gem: this.gems){
            if (gem != null){
                increment += gem.getVitality();
            }
        }
        return this.vitality + increment;
    }

    @Override
    public void addGem(int index, Gem gem){
            this.gems[index] = gem;
    }

    @Override
    public void removeGem(int index){
        this.gems[index] = null;
    }

    @Override
    public String toString() {
        return String.format("%s: %s-%s Damage, +%s Strength, +%s Agility, +%s Vitality",
                this.getName(), this.getMinDmg(), this.getMaxDmg(), this.getStrength(), this.getAgility(), this.getVitality());
    }

    @Override
    public int compareTo(Weapon o) {
        return Double.compare(this.getLevel(), o.getLevel());
    }
    @Override
    public String print(){
        return this.toString() + String.format(" (Item Level: %.1f)", this.getLevel());
    }
}
