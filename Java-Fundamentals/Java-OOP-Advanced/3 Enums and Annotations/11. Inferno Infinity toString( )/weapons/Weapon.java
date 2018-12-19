package weapons;

import gems.Gem;

public interface Weapon {

    void addGem(int index, Gem gem);

    void removeGem(int index);
}
