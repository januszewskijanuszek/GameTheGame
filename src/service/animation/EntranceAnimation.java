package service.animation;
/**
 * @author Patryk Januszewski
 */
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class EntranceAnimation {
    public static void fadeInAnimation(Object object, int duration){
        FadeTransition fade = new FadeTransition();
        fade.setNode((Node) object);
        fade.setCycleCount(1);
        fade.setDuration(Duration.millis(duration));
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public static void scaleIn(Object object, double scaleSize){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode((Node) object);
        scale.setCycleCount(1);
        scale.setDuration(Duration.millis(3000));
        scale.setByX(scaleSize);
        scale.setByY(scaleSize);
        scale.play();
    }
}
