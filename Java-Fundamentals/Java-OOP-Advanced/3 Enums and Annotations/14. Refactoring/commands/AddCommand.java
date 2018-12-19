package commands;

import contracts.Gem;
import factories.GemFactory;

public class AddCommand extends BaseCommand {

    @Override
    public String execute() {
            Gem gem = GemFactory.createGem(super.getParams()[3]);
            if ( super.getWeaponRepository().getAllWeapons().containsKey(super.getParams()[1])){
                super.getWeaponRepository().getAllWeapons().get(super.getParams()[1]).addGem(Integer.parseInt(super.getParams()[2]), gem);
            }
        return null;
    }
}
