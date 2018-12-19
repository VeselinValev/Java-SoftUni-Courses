package commands;

import annotations.WeaponInfo;
import contracts.Weapon;

import java.util.Arrays;

public class ReviewersCommand extends BaseCommand {
    @Override
    public String execute() {
        WeaponInfo weaponInfoReviewers = Weapon.class.getAnnotation(WeaponInfo.class);
        return "Reviewers: " + String.join(", ", Arrays.asList(weaponInfoReviewers.reviewers()));
    }
}
