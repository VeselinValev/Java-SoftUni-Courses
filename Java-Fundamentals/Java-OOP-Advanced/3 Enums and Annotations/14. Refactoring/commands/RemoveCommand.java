package commands;

public class RemoveCommand extends BaseCommand{
    @Override
    public String execute() {
        if ( super.getWeaponRepository().getAllWeapons().containsKey(super.getParams()[1])){
            super.getWeaponRepository().getAllWeapons().get(super.getParams()[1]).removeGem(Integer.parseInt(super.getParams()[2]));
        }
        return null;
    }
}
