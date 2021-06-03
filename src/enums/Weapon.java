package enums;

import javafx.scene.image.Image;

public enum Weapon {
    KNIFE(
            "Knife",
            1,
            3,
            90,
            75,
            1,
            0,
            new Image("file:src/textures/weapon/knife.png")),
    PISTOL(
            "Pistol",
            4,
            7,
            85,
            65,
            1,
            70,
            new Image("file:src/textures/weapon/glock.png")),
    BOLT_ACTION_RIFLE(
            "Bolt action rifle",
            8,
            12,
            85,
            65,
            1,
            140,
            new Image("file:src/textures/weapon/enfield.png")),
    MP5(
            "MP5",
            13,
            17,
            40,
            35,
            3,
            280,
            new Image("file:src/textures/weapon/mp5.png")),
    FN_FAL(
            "FN-FAL",
            18,
            21,
            70,
            60,
            1,
            560,
            new Image("file:src/textures/weapon/falafel.png")),
    AKM(
            "AKM",
            21,
            24,
            50,
            40,
            3,
            800,
            new Image("file:src/textures/weapon/ak47.png")),
    FLAMETHROWER(
            "Flamethrower",
            25,
            25,
            75,
            75,
            1,
            1200,
            new Image("file:src/textures/weapon/flamethrower.png"));


    private final String name;
    private final int damageBody;
    private final int damageHead;
    private final int hitChanceBody;
    private final int hitChanceHead;
    private final int hitCount;
    private final int cost;
    private final Image texture;


    Weapon(String name, int damageBody, int damageHead, int hitChanceBody, int hitChanceHead, int hitCount, int cost, Image texture) {
        this.name = name;
        this.damageBody = damageBody;
        this.damageHead = damageHead;
        this.hitChanceBody = hitChanceBody;
        this.hitChanceHead = hitChanceHead;
        this.hitCount = hitCount;
        this.cost = cost;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getDamageBody() {
        return damageBody;
    }

    public int getDamageHead() {
        return damageHead;
    }

    public int getHitChanceBody() {
        return hitChanceBody;
    }

    public int getHitChanceHead() {
        return hitChanceHead;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getCost() {
        return cost;
    }

    public Image getTexture() {
        return texture;
    }
}