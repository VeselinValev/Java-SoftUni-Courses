package commands;

import contracts.Weapon;
import factories.WeaponFactory;

public class CreateCommand extends BaseCommand{
    @Override
    public String execute() {
        Weapon weapon = WeaponFactory.createWeapon(super.getParams()[2], super.getParams()[1]);
        super.getWeaponRepository().addWeapon(weapon);
        return null;
    }
}
