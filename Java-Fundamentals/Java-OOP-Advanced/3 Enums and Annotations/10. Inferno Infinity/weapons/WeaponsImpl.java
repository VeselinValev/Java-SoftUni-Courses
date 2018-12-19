package weapons;

import enums.AxeAttributes;
import gems.Gem;

public abstract class WeaponsImpl implements Weapon{
    private String name;
    private int minDmg;
    private int maxDmg;
    private Gem[] gems;
    private int strength;
    private int agility;
    private int vitality;

    public WeaponsImpl(String name) {
        this.setName(name);
        this.setMinDmg(AxeAttributes.AXE_MIN_DMG.getValue());
        this.setMaxDmg(AxeAttributes.AXE_MAX_DMG.getValue());
        this.setGems(0);
        this.setStrength(0);
        this.setAgility(0);
        this.setVitality(0);
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getMinDmg() {
        for (Gem gem: this.gems){
            if (gem != null){
                this.minDmg += 2 * gem.getStrength() + gem.getAgility();
            }
        }
        return this.minDmg;
    }
    protected void setMinDmg(int minDmg){
        this.minDmg = minDmg;
    };

    public int getMaxDmg() {

        for (Gem gem: this.gems){
            if (gem  != null){
                this.maxDmg += 3 * gem.getStrength() + 4 * gem.getAgility();
            }
        }
        return this.maxDmg;
    }

    protected void setMaxDmg(int maxDmg){
        this.maxDmg = maxDmg;
    };

    protected void setGems(int numberOfSockets){
        this.gems = new Gem[numberOfSockets];
    };

    public int getStrength() {
        for (Gem gem: this.gems){
            if (gem != null){
                this.strength += gem.getStrength();
            }
        }
        return this.strength;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        for (Gem gem: this.gems){
            if (gem != null){
                this.agility += gem.getAgility();
            }
        }
        return this.agility;
    }

    private void setAgility(int agility) {

        this.agility = agility;
    }

    public int getVitality() {

        for (Gem gem: this.gems){
            if (gem != null){
                this.vitality += gem.getVitality();
            }
        }
        return this.vitality;
    }

    private void setVitality(int vitality) {
        this.vitality = vitality;
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
}
