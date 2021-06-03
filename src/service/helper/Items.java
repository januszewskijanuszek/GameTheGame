package service.helper;
/**
 * @author Patryk Januszewski
 */
import java.io.File;

public class Items {
    private static File file;
    private static int weapon;
    private static int bodyArmour;
    private static int headArmour;
    private static int money;
    private static int experience;
    private static double headArmourDurability;
    private static double bodyArmourDurability;

    public static File getFile() {
        return file;
    }

    public static int getWeapon() {
        return weapon;
    }

    public static double getHeadArmourDurability() {
        return headArmourDurability;
    }

    public static double getBodyArmourDurability() {
        return bodyArmourDurability;
    }

    public static int getBodyArmour() {
        return bodyArmour;
    }

    public static int getHeadArmour() {
        return headArmour;
    }

    public static int getMoney() {
        return money;
    }

    public static int getExperience() {
        return experience;
    }

    public static void setWeapon(int weapon) {
        Items.weapon = weapon;
    }

    public static void setBodyArmour(int bodyArmour) {
        Items.bodyArmour = bodyArmour;
    }

    public static void setHeadArmour(int headArmour) {
        Items.headArmour = headArmour;
    }

    public static void setMoney(int money) {
        Items.money = money;
    }

    public static void setExperience(int experience) {
        Items.experience = experience;
    }

    public static void setHeadArmourDurability(double headArmourDurability) {
        Items.headArmourDurability = headArmourDurability;
    }

    public static void setBodyArmourDurability(double bodyArmourDurability) {
        Items.bodyArmourDurability = bodyArmourDurability;
    }

    public static void setFile(File file) {
        Items.file = new File(file.getPath());
    }

    public static void vanish(){
        setWeapon(0);
        setBodyArmour(0);
        setHeadArmour(0);
        setMoney(0);
        setExperience(0);
        setHeadArmourDurability(100.0);
        setBodyArmourDurability(100.0);
    }
}
