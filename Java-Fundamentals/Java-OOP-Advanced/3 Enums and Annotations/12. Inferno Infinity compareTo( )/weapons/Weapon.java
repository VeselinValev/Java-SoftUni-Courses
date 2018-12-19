package weapons;

import gems.Gem;

public interface Weapon {

    double getLevel();

    void addGem(int index, Gem gem);

    void removeGem(int index);

    int compareTo(Weapon o);

    String print();
}
