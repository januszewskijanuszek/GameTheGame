package enums;

import javafx.scene.image.Image;

public enum Usable {
    CANCEL_RED(
            new Image("file:src/textures/usable/cancelRed.png")),
    CANCEL(
            new Image("file:src/textures/usable/cancel.png")),
    APPLY_GREEN(
            new Image("file:src/textures/usable/checkMarkGreen.png")),
    APPLY(
            new Image("file:src/textures/usable/checkMark.png")),
    ENTRY_DOOR(
            new Image("file:src/textures/usable/entryDoor.png")),
    ENTRY_DOOR_GREEN(
            new Image("file:src/textures/usable/entryDoorGreen.png")),
    EXIT_DOOR(
            new Image("file:src/textures/usable/exitDoor.png")),
    EXIT_DOOR_RED(
            new Image("file:src/textures/usable/exitDoorRed.png")),
    SAVE(
            new Image("file:src/textures/usable/save.png")),
    SAVE_BLUE(
            new Image("file:src/textures/usable/saveBlue.png")),
    MAIN_CON(
            new Image("file:src/textures/icon.png")),
    TUTORIAL(
            new Image("file:src/textures/usable/tutorial.png")),
    TUTORIAL_BLUE(
            new Image("file:src/textures/usable/tutorialBlue.png")),
    PRICE(
            new Image("file:src/textures/usable/price.png")),
    PRICE_GREEN(
            new Image("file:src/textures/usable/priceGreen.png")),
    COIN(
            new Image("file:src/textures/usable/coin.png")),
    COIN_YELLOW(
            new Image("file:src/textures/usable/coinYellow.png")),
    MAIN(
            new Image("file:src/textures/usable/menu.png")),
    INVENTORY(
            new Image("file:src/textures/usable/inventory.png")),
    INVENTORY_GRAY(
            new Image("file:src/textures/usable/inventoryGray.png")),
    SETTINGS(
            new Image("file:src/textures/usable/settings.png")),
    SETTINGS_GRAY(
            new Image("file:src/textures/usable/settingsGray.png")),
    TRASH(
            new Image("file:src/textures/usable/trash.png")),
    TRASH_RED(
            new Image("file:src/textures/usable/trashRed.png")),
    GRAVE(
            new Image("file:src/textures/usable/inventory.png")),
    WAR(
            new Image("file:src/textures/usable/war.png")),
    WAR_ORANGE(
            new Image("file:src/textures/usable/warOrange.png"));

    private final Image texture;

    Usable(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
}
