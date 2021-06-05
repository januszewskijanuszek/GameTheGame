package service;

import enums.Usable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.animation.OptionAnimation;
import service.helper.WindowChanger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SettingsController implements Initializable {

    @FXML private Label mainTitle;
    @FXML private ImageView exit;
    @FXML private Label setting;
    @FXML private CheckBox checkBoxFullscreen;
    @FXML private AnchorPane settingsWindow;

    OptionAnimation optionAnimation = new OptionAnimation();

    public void fullscreen(ActionEvent event){
        WindowChanger.fullscreen(checkBoxFullscreen.isSelected(), settingsWindow);
    }

    public void hoverExit(){
        optionAnimation.smoothScaleImage(exit,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        setting.setText("EXIT");
    }

    public void hoverFullscreen(){
        setting.setText("Set fullscreen");
    }

    public void clickExit(MouseEvent event) throws IOException {
        System.out.println("Switching to main Menu");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/MainMenu.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File settingFile = new File("src/saves/settings.txt");
        try {
            List<Integer> settingsList = new ArrayList<>();
            Scanner scanner = new Scanner(settingFile);
            while(scanner.hasNextLine()){
                settingsList.add(Integer.parseInt(scanner.nextLine()));
            }
            System.out.println("Fullscreen: " + settingsList.get(0));
        } catch (FileNotFoundException e) {
            System.out.println("Save file not found!");
        }
        System.out.println("Controller: SettingsController");
        setting.setText("");
    }
}
