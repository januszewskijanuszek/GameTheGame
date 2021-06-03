package enums;
/**
 * @author Patryk Januszewski
 */
import javafx.scene.image.Image;

public enum GameGui {
    BODY_SHOT(
            new Image("file:src/textures/gameGui/bodyShot.png")),
    BODY_SHOT_GREEN(
            new Image("file:src/textures/gameGui/bodyShotGreen.png")),
    HEAD_SHOT_GREEN(
            new Image("file:src/textures/gameGui/headShotGreen.png")),
    HEAD_SHOT(
            new Image("file:src/textures/gameGui/headShot.png")),
    SURRENDER(
            new Image("file:src/textures/gameGui/surrender.png")),
    SURRENDER_RED(
            new Image("file:src/textures/gameGui/surrenderRed.png")),
    SURRENDER_ICON(
            new Image("file:src/textures/gameGui/surrenderIcon.png")),
    DEATH(
            new Image("file:src/textures/gameGui/death.png")),
    WIN(
            new Image("file:src/textures/gameGui/win.png"));

    private final Image texture;

    GameGui(Image texture) {
        this.texture = texture;
    }

    public Image getTexture() {
        return texture;
    }
}
