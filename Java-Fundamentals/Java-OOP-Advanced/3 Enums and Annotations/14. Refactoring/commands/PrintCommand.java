package commands;

public class PrintCommand extends BaseCommand {
    @Override
    public String execute() {
        if ( super.getWeaponRepository().getAllWeapons().containsKey(super.getParams()[1])){
            return super.getWeaponRepository().getAllWeapons().get(super.getParams()[1]).toString();
        }
        return null;
    }
}
