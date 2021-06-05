package service;

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
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ChoseSaveController implements Initializable {

    @FXML private Label mainTitle;
    @FXML private ImageView saveImage1;
    @FXML private ImageView saveImage2;
    @FXML private ImageView saveImage3;

    File saveFile1 = new File("src/saves/save1.txt");
    File saveFile2 = new File("src/saves/save2.txt");
    File saveFile3 = new File("src/saves/save3.txt");

    OptionAnimation optionAnimation = new OptionAnimation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(!saveFile1.exists()){
            saveImage1.setImage(Usable.CROSS.getTexture());
        }
        if(!saveFile2.exists()){
            saveImage2.setImage(Usable.CROSS.getTexture());
        }
        if(!saveFile3.exists()){
            saveImage3.setImage(Usable.CROSS.getTexture());
        }

        SmoothMoveAnimation.smoothAnimation(mainTitle);
        EntranceAnimation.scaleIn(mainTitle, 1.2);
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        EntranceAnimation.fadeInAnimation(saveImage1, 2000);
        EntranceAnimation.fadeInAnimation(saveImage2, 2000);
        EntranceAnimation.fadeInAnimation(saveImage3, 2000);
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
    // --------- Hovers ----------------
    public void saveHover1(){
        if(saveFile1.exists()){
            optionAnimation.smoothScaleImage(saveImage1,
                    Usable.SAVE_BLUE.getTexture(),
                    Usable.SAVE.getTexture());
            mainTitle.setText("Save Slot 1");
        } else {
            optionAnimation.smoothScaleImage(saveImage1,
                    Usable.CROSS_GREEN.getTexture(),
                    Usable.CROSS.getTexture());
            mainTitle.setText("Create save 1");
        }
    }
    public void saveHover2(){
        if(saveFile2.exists()){
            optionAnimation.smoothScaleImage(saveImage2,
                    Usable.SAVE_BLUE.getTexture(),
                    Usable.SAVE.getTexture());
            mainTitle.setText("Save Slot 2");
        } else {
            optionAnimation.smoothScaleImage(saveImage2,
                    Usable.CROSS_GREEN.getTexture(),
                    Usable.CROSS.getTexture());
            mainTitle.setText("Create save 2");
        }
    }
    public void saveHover3(){
        if(saveFile3.exists()){
            optionAnimation.smoothScaleImage(saveImage3,
                    Usable.SAVE_BLUE.getTexture(),
                    Usable.SAVE.getTexture());
            mainTitle.setText("Save Slot 3");
        } else {
            optionAnimation.smoothScaleImage(saveImage3,
                    Usable.CROSS_GREEN.getTexture(),
                    Usable.CROSS.getTexture());
            mainTitle.setText("Create save 3");
        }
    }
    //private void createFile(File file) throws IOException {
    //    if(file.createNewFile()){
    //        PrintWriter printWriter = new PrintWriter(file);
    //        printWriter.println(0);
    //        printWriter.println(0);
    //        printWriter.println(0);
    //        printWriter.println(0);
    //        printWriter.println(0);
    //        printWriter.println(100.0);
    //        printWriter.println(100.0);
    //    }
    //}
}
