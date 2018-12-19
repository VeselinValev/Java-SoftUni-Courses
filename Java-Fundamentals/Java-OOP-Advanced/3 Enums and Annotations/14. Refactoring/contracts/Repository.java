package contracts;

import java.util.Map;

public interface Repository<Weapon> {
    void addWeapon(Weapon weapon);

    Map<String, Weapon> getAllWeapons();
}
