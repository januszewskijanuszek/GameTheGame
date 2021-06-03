package service.helper;
/**
 * @author Patryk Januszewski
 */
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WindowChanger {
    public static void fullscreen(boolean choice, AnchorPane window){
        Stage stage = (Stage) window.getScene().getWindow();
        stage.setFullScreen(choice);
    }
}
