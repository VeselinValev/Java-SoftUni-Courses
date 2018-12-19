package commands;

import annotations.Inject;
import contracts.Executable;
import contracts.Repository;
import contracts.Weapon;

public abstract class BaseCommand implements Executable {

    @Inject
    private String[] params;
    @Inject
    private Repository<Weapon> weaponRepository;

    public String[] getParams() {
        return this.params;
    }

    public Repository<Weapon> getWeaponRepository() {
        return this.weaponRepository;
    }
}
