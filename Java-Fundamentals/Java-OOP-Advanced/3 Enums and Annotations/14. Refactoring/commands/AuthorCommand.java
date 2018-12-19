package commands;

import annotations.WeaponInfo;
import contracts.Weapon;

public class AuthorCommand extends BaseCommand {
    @Override
    public String execute() {
        WeaponInfo weaponInfoAuthor = Weapon.class.getAnnotation(WeaponInfo.class);
        return "Author: " + weaponInfoAuthor.author();
    }
}
