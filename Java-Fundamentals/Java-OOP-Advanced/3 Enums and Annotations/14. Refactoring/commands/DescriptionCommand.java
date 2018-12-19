package commands;

import annotations.WeaponInfo;
import contracts.Weapon;

public class DescriptionCommand extends BaseCommand {
    @Override
    public String execute() {
        WeaponInfo weaponInfoDescription = Weapon.class.getAnnotation(WeaponInfo.class);
        return "Class description: " + weaponInfoDescription.description();
    }
}
