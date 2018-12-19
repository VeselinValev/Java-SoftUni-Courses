package weapons;

import Annotations.WeaponInfo;
import gems.Gem;

@WeaponInfo(author = "Pesho", revision = 3, description = "Used for Java OOP Advanced course - Enumerations and Annotations.", reviewers = {"Pesho", "Svetlio"})
public interface Weapon {

    double getLevel();

    void addGem(int index, Gem gem);

    void removeGem(int index);

    int compareTo(Weapon o);

    String print();
}
