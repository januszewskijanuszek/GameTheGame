package service.animation;
/**
 * @author Patryk Januszewski
 */
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class SmoothMoveAnimation {
    public static void smoothAnimation(Object object){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode((Node) object);
        translate.setDuration(Duration.millis(3000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(10);
        translate.setByY(2);
        translate.setAutoReverse(true);
        translate.play();

        RotateTransition rotate = new RotateTransition();
        rotate.setNode((Node) object);
        rotate.setDuration(Duration.millis(5000));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setAutoReverse(true);
        rotate.setByAngle(2);
        rotate.play();
    }
}
