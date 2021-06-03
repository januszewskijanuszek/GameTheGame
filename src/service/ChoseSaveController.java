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
import javafx.stage.Stage;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ChoseSaveController implements Initializable {

    @FXML private Label mainTitle;
    @FXML private ImageView saveImage1;
    @FXML private ImageView saveImage2;
    @FXML private ImageView saveImage3;

    OptionAnimation optionAnimation = new OptionAnimation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        EntranceAnimation.scaleIn(mainTitle, 1.2);
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        EntranceAnimation.fadeInAnimation(saveImage1, 2000);
        EntranceAnimation.fadeInAnimation(saveImage2, 2000);
        EntranceAnimation.fadeInAnimation(saveImage3, 200);
    }

    public void clickedSave1(MouseEvent event) throws IOException {
        File file = new File("src/saves/save1.txt");
        setSceneToGame(file ,event);
    }
    public void clickedSave2(MouseEvent event) throws IOException {
        File file = new File("src/saves/save2.txt");
        setSceneToGame(file ,event);
    }
    public void clickedSave3(MouseEvent event) throws IOException {
        File file = new File("src/saves/save3.txt");
        setSceneToGame(file ,event);
    }

    private void setSceneToGame(File file,MouseEvent event) throws IOException {
        //List<Integer> saveFile = new ArrayList<>();
        System.out.println(file.getPath());
        Items.setFile(file);
        Scanner scanner = new Scanner(file);
        //while (scanner.hasNextLine()) saveFile.add(Integer.parseInt(scanner.nextLine()));
        Items.setWeapon(
                Integer.parseInt(scanner.nextLine()));
        Items.setBodyArmour(
                Integer.parseInt(scanner.nextLine()));
        Items.setHeadArmour(
                Integer.parseInt(scanner.nextLine()));
        Items.setMoney(
                Integer.parseInt(scanner.nextLine()));
        Items.setExperience(
                Integer.parseInt(scanner.nextLine()));
        Items.setBodyArmourDurability(
                Double.parseDouble(scanner.nextLine()));
        Items.setHeadArmourDurability(
                Double.parseDouble(scanner.nextLine()));
        System.out.println("Switching to Game");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Game.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }

    public void saveHover1(MouseEvent event){
        optionAnimation.smoothScaleImage(saveImage1,
                Usable.SAVE_BLUE.getTexture(),
                Usable.SAVE.getTexture());
        mainTitle.setText("Save Slot 1");
    }
    public void saveHover2(MouseEvent event){
        optionAnimation.smoothScaleImage(saveImage2,
                Usable.SAVE_BLUE.getTexture(),
                Usable.SAVE.getTexture());
        mainTitle.setText("Save Slot 2");
    }
    public void saveHover3(MouseEvent event){
        optionAnimation.smoothScaleImage(saveImage3,
                Usable.SAVE_BLUE.getTexture(),
                Usable.SAVE.getTexture());
        mainTitle.setText("Save Slot 3");
    }
}
