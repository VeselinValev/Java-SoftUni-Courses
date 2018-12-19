package repositories;

import contracts.Repository;
import contracts.Weapon;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class WeaponRepository implements Repository<Weapon> {
    private Map<String, Weapon> allWeapons;

    public WeaponRepository() {
        this.allWeapons = new LinkedHashMap<>();
    }
    @Override
    public void addWeapon(Weapon weapon){
        this.allWeapons.putIfAbsent(weapon.getName(), weapon);
    }
    @Override
    public Map<String, Weapon> getAllWeapons() {
        return Collections.unmodifiableMap(this.allWeapons);
    }
}
