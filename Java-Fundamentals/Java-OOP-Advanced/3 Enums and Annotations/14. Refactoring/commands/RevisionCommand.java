package commands;

import annotations.WeaponInfo;
import contracts.Weapon;

public class RevisionCommand extends BaseCommand {
    @Override
    public String execute() {
        WeaponInfo weaponInfoRevision = Weapon.class.getAnnotation(WeaponInfo.class);
        return "Revision: " + weaponInfoRevision.revision();
    }
}
