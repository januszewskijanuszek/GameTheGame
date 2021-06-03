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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class GameController implements Initializable{

    @FXML private Label mainTitle;
    @FXML private ImageView exitIcon;
    @FXML private ImageView warIcon;
    @FXML private ImageView inventoryIcon;
    @FXML private ImageView trashIcon;
    @FXML private ImageView shopIcon;
    @FXML private ImageView saveIcon;
    @FXML private ImageView yes;
    @FXML private ImageView no;
    @FXML private ImageView yesSave;
    @FXML private Label bottomLabel;

    @FXML private AnchorPane game;
    @FXML private GridPane mainGrid;

    private final int TITLE_FADE = 1000;
    private boolean isVanish;

    OptionAnimation optionAnimation = new OptionAnimation();

    //    ---------- On Click --------
    public void clickShop(MouseEvent event) throws IOException {
        System.out.println("Switching to main Menu");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Shop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickTrash(MouseEvent event){
        isVanish = true;
        setGui(false);
        yesSave.setVisible(false);
    }
    public void clickInventory(MouseEvent event) throws IOException {
        System.out.println("Switching to Inventory");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Inventory.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickYesSave(MouseEvent event){
        setGui(true);
        EntranceAnimation.fadeInAnimation(mainGrid, 2000);
    }
    public void clickSave(MouseEvent event) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(Items.getFile());
        printWriter.flush();
        printWriter.println(Items.getWeapon());
        printWriter.println(Items.getBodyArmour());
        printWriter.println(Items.getHeadArmour());
        printWriter.println(Items.getMoney());
        printWriter.println(Items.getExperience());
        printWriter.println(Items.getBodyArmourDurability());
        printWriter.println(Items.getHeadArmourDurability());
        printWriter.close();
        setGui(false);
        no.setVisible(false);
        yes.setVisible(false);
        EntranceAnimation.fadeInAnimation(yesSave, 2000);
        yesSave.setVisible(true);
        bottomLabel.setText("Your progress has been saved!");
    }
    public void clickExit(MouseEvent event){
        setGui(false);
        yesSave.setVisible(false);
        mainTitle.setText("Are you sure?");
        EntranceAnimation.fadeInAnimation(no, 2000);
        EntranceAnimation.fadeInAnimation(yes, 2000);
    }
    public void clickYes(MouseEvent event) throws FileNotFoundException {
        if(isVanish){
            Items.vanish();
            clickNo(event);
            clickSave(event);
        } else {
            exit();
        }
    }
    public void clickNo(MouseEvent event){
        setGui(true);
        isVanish = false;
        EntranceAnimation.fadeInAnimation(mainGrid, TITLE_FADE);
    }
    //    ---------- Hovers ---------
    public void yesSaveHover(){
        optionAnimation.smoothScaleImage(yesSave,
                Usable.APPLY_GREEN.getTexture(),
                Usable.APPLY.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Your progress has been saved!");
    }
    public void noHover(MouseEvent event){
        optionAnimation.smoothScaleImage(no,
                Usable.CANCEL_RED.getTexture(),
                Usable.CANCEL.getTexture());
        bottomLabel.setText("NO");
    }
    public void yesHover(MouseEvent event){
        optionAnimation.smoothScaleImage(yes,
                Usable.APPLY_GREEN.getTexture(),
                Usable.APPLY.getTexture());
        bottomLabel.setText("YES");
    }

    public void exitHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(exitIcon,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setText("EXIT");
        bottomLabel.setText("");
    }

    public void trashHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(trashIcon,
                Usable.TRASH_RED.getTexture(),
                Usable.TRASH.getTexture());
        mainTitle.setTextFill(Color.RED);
        mainTitle.setText("RESET PROGRESS");
        bottomLabel.setText("");
    }

    public void inventoryHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(inventoryIcon,
                Usable.INVENTORY_GRAY.getTexture(),
                Usable.INVENTORY.getTexture());
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setText("INVENTORY");
        bottomLabel.setText("");
    }

    public void warHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(warIcon,
                Usable.WAR_ORANGE.getTexture(),
                Usable.WAR.getTexture());
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setText("BATTLE");
        bottomLabel.setText("");
    }

    public void shopHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(shopIcon,
                Usable.PRICE_GREEN.getTexture(),
                Usable.PRICE.getTexture());
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setText("SHOP");
        bottomLabel.setText("");
    }

    public void saveHover(MouseEvent event){
        EntranceAnimation.fadeInAnimation(mainTitle, TITLE_FADE);
        optionAnimation.smoothScaleImage(saveIcon,
                Usable.SAVE_BLUE.getTexture(),
                Usable.SAVE.getTexture());
        mainTitle.setTextFill(Color.WHITE);
        mainTitle.setText("SAVE");
        bottomLabel.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bottomLabel.setText("");
        SmoothMoveAnimation.smoothAnimation(bottomLabel);
        setGui(true);
        System.out.println("Body weapon: " + Items.getWeapon());
        System.out.println("Body armour: " + Items.getBodyArmour());
        System.out.println("head armour: " + Items.getHeadArmour());
        System.out.println("Money: " + Items.getMoney());
        System.out.println("experience: " + Items.getExperience());
        System.out.println("Body Durability: " + Items.getBodyArmourDurability());
        System.out.println("Head Durability: " + Items.getHeadArmourDurability());
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        EntranceAnimation.fadeInAnimation(mainGrid, 2000);
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        //SmoothMoveAnimation.smoothAnimation(mainGrid);
    }

    private void setGui(boolean choice){
        yesSave.setVisible(!choice);
        no.setVisible(!choice);
        yes.setVisible(!choice);
        exitIcon.setVisible(choice);
        warIcon.setVisible(choice);
        inventoryIcon.setVisible(choice);
        trashIcon.setVisible(choice);
        saveIcon.setVisible(choice);
        shopIcon.setVisible(choice);
    }
    private void exit(){
        System.out.println("exiting program");
        Stage stage = (Stage) game.getScene().getWindow();
        stage.close();
    }
}
