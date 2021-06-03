package enums;
/**
 * @author Patryk Januszewski
 */
import javafx.scene.image.Image;

public enum Shop {
    BODY_ARMOUR_SHOP(
            new Image("file:src/textures/shop/bodyArmourShop.png")),
    BODY_ARMOUR_SHOP_GREEN(
            new Image("file:src/textures/shop/bodyArmourShopGreen.png")),
    HEAD_ARMOUR_SHOP(
            new Image("file:src/textures/shop/headArmourShop.png")),
    HEAD_ARMOUR_SHOP_GREEN(
            new Image("file:src/textures/shop/headArmourShopGreen.png")),
    WEAPON_SHOP(
            new Image("file:src/textures/shop/weaponShop.png")),
    WEAPON_SHOP_GREEN(
            new Image("file:src/textures/shop/weaponShopGreen.png")),
    REPAIR_SHOP(
            new Image("file:src/textures/shop/repairShop.png")),
    REPAIR_SHOP_GREEN(
            new Image("file:src/textures/shop/repairShopGreen.png"));

    private final Image texture;

    Shop(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
}
