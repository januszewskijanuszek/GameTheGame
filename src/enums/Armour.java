package enums;

import javafx.scene.image.Image;

public enum Armour {
    NONE(
            "No armour",
            9999,
            0,
            0,
            new Image("file:src/textures/usable/cancel.png")),
    LIGHT_VEST(
            "Light bulletproof vest",
            60,
            20,
            150,
            new Image("file:src/textures/armour/body/armourTier1.png")),
    VEST(
            "Light bulletproof vest",
            70,
            40,
            300,
            new Image("file:src/textures/armour/body/armourTier2.png")),
    HEAVY_VEST(
            "Light bulletproof vest",
            80,
            60,
            450,
            new Image("file:src/textures/armour/body/armourTier3.png"));

    private final String name;
    private final int durability;
    private final int absorption;
    private final int cost;
    private final Image texture;

    Armour(String name, int durability, int absorption, int cost, Image texture) {
        this.name = name;
        this.durability = durability;
        this.absorption = absorption;
        this.cost = cost;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability;
    }

    public int getAbsorption() {
        return absorption;
    }

    public int getCost() {
        return cost;
    }

    public Image getTexture() {
        return texture;
    }
}
