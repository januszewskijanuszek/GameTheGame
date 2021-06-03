package service;
/**
 * @author Patryk Januszewski
 */
import enums.Usable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import service.animation.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class menuController implements Initializable {

    @FXML private Label titleText;
    @FXML private ImageView mainImage;
    @FXML private ImageView exit;
    @FXML private Label setting;
    @FXML private ImageView start;
    @FXML private ImageView settings;
    @FXML private ImageView no;
    @FXML private ImageView yes;
    @FXML private AnchorPane startWindow;

    private Media media;
    private MediaPlayer mediaPlayer;

    OptionAnimation optionAnimation = new OptionAnimation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        File settingsFile = new File("src/saves/settings.txt");
        File saveFile1 = new File("src/saves/save1.txt");
        File saveFile2 = new File("src/saves/save2.txt");
        File saveFile3 = new File("src/saves/save3.txt");

        // Loading settings file
        if(settingsFile.exists()){
            try {
                Scanner scanner = new Scanner(settingsFile);
                System.out.println("settingsFile exist!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("settings file does not exist");
            try {
                PrintWriter printWriter = new PrintWriter(settingsFile);
                settingsFile.createNewFile();
                printWriter.println(0);
                titleText.setText("Restart Game!");
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Loading info from files to List!
        if(saveFile1.exists()
                && saveFile2.exists()
                && saveFile3.exists()){
            try {
                Scanner scanner1 = new Scanner(saveFile1);
                Scanner scanner2 = new Scanner(saveFile2);
                Scanner scanner3 = new Scanner(saveFile3);
                System.out.println("saveFile1 exist!");
                System.out.println("saveFile2 exist!");
                System.out.println("saveFile3 exist!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PrintWriter printWriter1 = new PrintWriter(saveFile1);
                PrintWriter printWriter2 = new PrintWriter(saveFile2);
                PrintWriter printWriter3 = new PrintWriter(saveFile3);
                saveFile1.createNewFile();
                saveFile2.createNewFile();
                saveFile3.createNewFile();
                for(int i = 0 ; i < 5 ; i++){
                    printWriter1.println(0);
                    printWriter2.println(0);
                    printWriter3.println(0);
                }
                titleText.setText("Restart Game!");
                printWriter1.close();
                printWriter2.close();
                printWriter3.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Controller: menuController");
        no.setVisible(false);
        yes.setVisible(false);
        EntranceAnimation.fadeInAnimation(settings,2000);
        EntranceAnimation.fadeInAnimation(start, 2000);
        EntranceAnimation.fadeInAnimation(exit, 2000);
        // media = new Media("file:src/sound/mainMusic.mp3");
        // mediaPlayer = new MediaPlayer(media);
        // mediaPlayer.play();
        animation(setting);
        animation(mainImage);
        animation(titleText);
    }

    public void hoverExit(MouseEvent event){
        optionAnimation.smoothScaleImage(exit,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(setting, 1000);
        setting.setText("EXIT");
    }
    public void hoverStart(MouseEvent event){
        optionAnimation.smoothScaleImage(start,
                Usable.ENTRY_DOOR_GREEN.getTexture(),
                Usable.ENTRY_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(setting, 1000);
        setting.setText("START");
    }
    public void hoverSettings(MouseEvent event){
        optionAnimation.smoothScaleImage(settings,
                Usable.SETTINGS_GRAY.getTexture(),
                Usable.SETTINGS.getTexture());
        EntranceAnimation.fadeInAnimation(setting, 1000);
        setting.setText("SETTINGS");
    }
    public void hoverNo(MouseEvent event){
        optionAnimation.smoothScaleImage(no,
                Usable.CANCEL_RED.getTexture(),
                Usable.CANCEL.getTexture());
        EntranceAnimation.fadeInAnimation(setting, 1000);
        setting.setText("NO");
    }
    public void hoverYes(MouseEvent event){
        optionAnimation.smoothScaleImage(yes,
                Usable.APPLY_GREEN.getTexture(),
                Usable.APPLY.getTexture());
        EntranceAnimation.fadeInAnimation(setting, 2000);
        setting.setText("YES");
    }
    public void clickExit(MouseEvent event){
        setting.setText("essa1");
        setGui(false);
        EntranceAnimation.fadeInAnimation(no, 2000);
        EntranceAnimation.fadeInAnimation(yes, 2000);
        EntranceAnimation.fadeInAnimation(titleText, 2000);
        titleText.setText("REALLY?");
    }
    public void clickNo(MouseEvent event){
        titleText.setText("Good choice");
        setGui(true);
    }
    public void clickYes(MouseEvent event){
        exit();
    }
    public void clickSettings(MouseEvent event) throws IOException {
        System.out.println("Switching to settings");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Settings.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickStart(MouseEvent event) throws IOException {
        System.out.println("Switching to Game");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/ChoseSave.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }

    // Private
    private void exit(){
        System.out.println("exiting program");
        Stage stage = (Stage) startWindow.getScene().getWindow();
        stage.close();
    }
    private void setGui(boolean choice){
        settings.setVisible(choice);
        exit.setVisible(choice);
        start.setVisible(choice);
        no.setVisible(!choice);
        yes.setVisible(!choice);
        EntranceAnimation.fadeInAnimation(settings, 2000);
        EntranceAnimation.fadeInAnimation(exit, 2000);
        EntranceAnimation.fadeInAnimation(start, 2000);
    }
    private void animation(Object object){
        EntranceAnimation.scaleIn(object, 1.1);
        EntranceAnimation.fadeInAnimation(object, 2000);
        SmoothMoveAnimation.smoothAnimation(object);
    }
}