package service;

import enums.Usable;
import enums.Weapon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WeaponShopController implements Initializable {
    @FXML private GridPane gridPane;

    @FXML private Label infoLabel;
    @FXML private Label mainTitle;
    @FXML private Label moneyLabel;

    @FXML private ImageView pistolImage;
    @FXML private ImageView boltActionRifleImage;
    @FXML private ImageView Mp5Image;
    @FXML private ImageView falafelImage;
    @FXML private ImageView AKMImage;
    @FXML private ImageView FlamethrowerImage;
    @FXML private ImageView imageShifter;
    @FXML private ImageView moneyImage;
    @FXML private ImageView exitImage;

    OptionAnimation optionAnimation = new OptionAnimation();
    // ---------- On Click -----------
    public void clickPistol(){
        checkAffordable(Weapon.PISTOL);
    }
    public void clickBoltActionRifleImage(){
        checkAffordable(Weapon.BOLT_ACTION_RIFLE);
    }
    public void clickMp5(){
        checkAffordable(Weapon.MP5);
    }
    public void clickFalafel(){
        checkAffordable(Weapon.FN_FAL);
    }
    public void clickAKM(){
        checkAffordable(Weapon.AKM);
    }
    public void clickFlamethrower(){
        checkAffordable(Weapon.FLAMETHROWER);
    }
    public void clickExit(MouseEvent event) throws IOException {
        System.out.println("Switching to Game");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Shop.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    // ---------- Hovers ------------
    public void pistolHover(){
        optionAnimation.smoothScaleImage(pistolImage,
                Weapon.PISTOL.getTexture(),
                Weapon.PISTOL.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.PISTOL, infoLabel);
    }
    public void boltActionRifleHover(){
        optionAnimation.smoothScaleImage(boltActionRifleImage,
                Weapon.BOLT_ACTION_RIFLE.getTexture(),
                Weapon.BOLT_ACTION_RIFLE.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.BOLT_ACTION_RIFLE, infoLabel);
    }
    public void mp5Hover(){
        optionAnimation.smoothScaleImage(Mp5Image,
                Weapon.MP5.getTexture(),
                Weapon.MP5.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.MP5, infoLabel);
    }
    public void falafelHover(){
        optionAnimation.smoothScaleImage(falafelImage,
                Weapon.FN_FAL.getTexture(),
                Weapon.FN_FAL.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.FN_FAL, infoLabel);
    }
    public void AKMHover(){
        optionAnimation.smoothScaleImage(AKMImage,
                Weapon.AKM.getTexture(),
                Weapon.AKM.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.AKM, infoLabel);
    }
    public void flamethrowerHover(){
        optionAnimation.smoothScaleImage(FlamethrowerImage,
                Weapon.FLAMETHROWER.getTexture(),
                Weapon.FLAMETHROWER.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        changeInfo(Weapon.FLAMETHROWER, infoLabel);
    }
    public void exitHover(){
        optionAnimation.smoothScaleImage(exitImage,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        infoLabel.setText("EXIT");
    }
    public void moneyHover(){
        optionAnimation.smoothScaleImage(moneyImage,
                Usable.COIN_YELLOW.getTexture(),
                Usable.COIN.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        infoLabel.setText("Current Money");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageShifter.setOpacity(0.3);
        imageShifter.setCache(false);
        imageShifter.setScaleY(3);
        imageShifter.setScaleX(3);
        infoLabel.setText("");
        moneyLabel.setText("" + Items.getMoney());
        EntranceAnimation.fadeInAnimation(gridPane, 2000);
        SmoothMoveAnimation.smoothAnimation(infoLabel);
        SmoothMoveAnimation.smoothAnimation(mainTitle);
    }
    private void checkAffordable(Weapon weapon){
        if(weapon.getCost() > Items.getMoney()){
            mainTitle.setTextFill(Color.RED);
            mainTitle.setText("You can't afford!");
        } else if(Items.getWeapon() >= weapon.ordinal()){
            mainTitle.setTextFill(Color.ORANGE);
            mainTitle.setText("You have better armour!");
        } else {
            Items.setWeapon(weapon.ordinal());
            mainTitle.setTextFill(Color.GREEN);
            mainTitle.setText("Thanks for purchase!");
            Items.setWeapon(weapon.ordinal());
            Items.setMoney(Items.getMoney() - weapon.getCost());
            moneyLabel.setText("" + Items.getMoney());
        }
    }
    private void changeInfo(Weapon weapon,Label label){
        imageShifter.setImage(weapon.getTexture());
        label.setText(weapon.getName() +
                "\n Damage:" +
                "\n - Head: " + weapon.getDamageHead() +
                "\n - Body: " + weapon.getDamageBody() +
                "\n Hit chance:" +
                "\n - Head: " + weapon.getHitChanceHead() + "%" +
                "\n - Body: " + weapon.getHitChanceBody() + "%" +
                "\n Price: " + weapon.getCost() + "$");
    }
}
