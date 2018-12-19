package commands;

public class CompareCommand extends BaseCommand {
    @Override
    public String execute() {
        if ( super.getWeaponRepository().getAllWeapons().containsKey(super.getParams()[1]) &&
                super.getWeaponRepository().getAllWeapons().containsKey(super.getParams()[2])){
            if (super.getWeaponRepository().getAllWeapons().get(super.getParams()[1])
                    .compareTo(super.getWeaponRepository().getAllWeapons().get(super.getParams()[2])) > 0) {
                return super.getWeaponRepository().getAllWeapons().get(super.getParams()[1]).print();
            } else {
                return super.getWeaponRepository().getAllWeapons().get(super.getParams()[2]).print();
            }
        }
        return null;
    }
}
