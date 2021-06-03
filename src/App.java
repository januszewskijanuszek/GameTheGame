import enums.Usable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        // Loading main files
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("scenes/MainMenu.fxml"))); // Loading fxml
        Scene scene = new Scene(root);  // Setting root to scene
        String css1 = Objects.requireNonNull(this.getClass().getResource("style/style1.css")).toExternalForm(); // Setting css file

        scene.getStylesheets().add(css1);
        stage.getIcons().add(Usable.MAIN_CON.getTexture());
        stage.setTitle("Game The Game"); // Setting a title
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
