package service.helper;

import enums.Armour;
import enums.Head;
import enums.Weapon;

public class ItemsSelector {
    public static Weapon getWeapon(int weaponId){
        return Weapon.values()[weaponId];
    }
    public static Armour getBodyArmour(int bodyArmourId){
        return Armour.values()[bodyArmourId];
    }
    public static Head getHeadArmour(int headArmourId){
        return Head.values()[headArmourId];
    }
}
