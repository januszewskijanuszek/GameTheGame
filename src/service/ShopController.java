package service;
/**
 * @author Patryk Januszewski
 */

import enums.Shop;
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
import javafx.stage.Stage;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    @FXML private AnchorPane mainFrame;
    @FXML private GridPane mainGrid;
    @FXML private Label bottomLabel;
    @FXML private Label mainTitle;


    @FXML private ImageView bodyArmourShopImage;
    @FXML private ImageView headArmourShopImage;
    @FXML private ImageView weaponShopImage;
    @FXML private ImageView repairShopImage;
    @FXML private ImageView exitImage;

    OptionAnimation optionAnimation = new OptionAnimation();
    // ------------ On Click ---------
    public void clickExit(MouseEvent event) throws IOException {
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
    public void clickHeadArmourShop(MouseEvent event) throws IOException {
        System.out.println("Switching to Head Armour Shop");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/HeadArmourShop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickBodyArmourShop(MouseEvent event) throws IOException {
        System.out.println("Switching to Body Armour Shop");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/BodyArmourShop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickWeaponShop(MouseEvent event) throws IOException {
        System.out.println("Switching to Body Armour Shop");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/WeaponShop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    public void clickRepairShop(MouseEvent event) throws IOException {
        System.out.println("Switching to Body Repair");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/RepairShop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    // ------------ Hover -----------
    public void repairHover(MouseEvent event){
        optionAnimation.smoothScaleImage(repairShopImage,
                Shop.REPAIR_SHOP_GREEN.getTexture(),
                Shop.REPAIR_SHOP.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Repair");
    }
    public void weaponHover(MouseEvent event){
        optionAnimation.smoothScaleImage(weaponShopImage,
                Shop.WEAPON_SHOP_GREEN.getTexture(),
                Shop.WEAPON_SHOP.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Weapon");
    }
    public void headArmourHover(MouseEvent event){
        optionAnimation.smoothScaleImage(headArmourShopImage,
                Shop.HEAD_ARMOUR_SHOP_GREEN.getTexture(),
                Shop.HEAD_ARMOUR_SHOP.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Head Armour");
    }
    public void bodyArmourHover(MouseEvent event){
        optionAnimation.smoothScaleImage(bodyArmourShopImage,
                Shop.BODY_ARMOUR_SHOP_GREEN.getTexture(),
                Shop.BODY_ARMOUR_SHOP.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Body Armour");
    }
    public void exitHover(MouseEvent event){
        optionAnimation.smoothScaleImage(exitImage,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("EXIT");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bottomLabel.setText("");
        EntranceAnimation.fadeInAnimation(exitImage, 2000);
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        EntranceAnimation.fadeInAnimation(mainGrid, 2000);
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        SmoothMoveAnimation.smoothAnimation(bottomLabel);
    }
}