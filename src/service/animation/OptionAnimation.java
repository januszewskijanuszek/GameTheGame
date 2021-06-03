package service.animation;
/**
 * @author Patryk Januszewski
 */
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class OptionAnimation {

    private boolean isChecked;

    public void smoothScale(Object object){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode((Node) object);
        scale.setDuration(Duration.millis(500));
        if(isChecked){
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
        } else {
            scale.setToX(1.2);
            scale.setToY(1.2);
            scale.play();
        }
        isChecked = !isChecked;
    }

    public void smoothScaleImage(ImageView imageView, Image inImage, Image outImage){
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(500));
        scale.setNode(imageView);
        if(isChecked){
            imageView.setImage(outImage);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
        } else {
            imageView.setImage(inImage);
            scale.setToX(1.2);
            scale.setToY(1.2);
            scale.play();
        }
        isChecked = !isChecked;
    }
    public void smoothsScaleProgressBar(ProgressBar progressBar, Label label, Color color){
        ScaleTransition scale = new ScaleTransition();
        ScaleTransition scaleLabel = new ScaleTransition();
        scale.setDuration(Duration.millis(500));
        scaleLabel.setNode(label);
        scale.setNode(progressBar);
        if(isChecked){
            label.setVisible(false);
            scaleLabel.setToX(1.0);
            scaleLabel.setToY(1.0);
            scale.setToX(1.0);
            scale.setToY(1.0);
            scale.play();
            scaleLabel.play();
        } else {
            label.setTextFill(color);
            label.setVisible(true);
            scaleLabel.setToX(1.2);
            scaleLabel.setToY(1.2);
            scale.setToX(1.2);
            scale.setToY(1.2);
            scale.play();
            scaleLabel.play();
        }
        isChecked = !isChecked;
    }
}
