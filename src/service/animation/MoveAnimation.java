package service.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class MoveAnimation {
    public static void moveRight(Object object){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode((Node) object);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(1);
        translate.setToX(25);
        translate.setAutoReverse(true);
        translate.play();
    }
}
