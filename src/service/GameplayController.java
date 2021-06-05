package service;
/**
 * @author Patryk Januszewski
 */

import enums.Armour;
import enums.GameGui;
import enums.Head;
import enums.Weapon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;
import service.helper.ItemsSelector;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameplayController implements Initializable {

    @FXML private Label mainTitle;
    @FXML private Label myLogLabel;
    @FXML private Label enemyLogLabel;
    @FXML private Label itemInfoLabel;

    // Users GUI
    @FXML private GridPane myGrid;
    @FXML private ImageView myBodyImage;
    @FXML private ImageView myHeadImage;
    @FXML private ImageView myWeaponImage;
    @FXML private ProgressBar myArmourProgressBar;
    @FXML private ProgressBar myHeadProgressBar;
    @FXML private ProgressBar myHpBar;
    @FXML private ImageView surrenderIcon;
    @FXML private ImageView headAttackIcon;
    @FXML private ImageView bodyAttackIcon;
    @FXML private ImageView myCross;

    private Weapon myWeapon = ItemsSelector.getWeapon(Items.getWeapon());
    private Armour myArmour = ItemsSelector.getBodyArmour(Items.getBodyArmour());
    private Head myHead = ItemsSelector.getHeadArmour(Items.getHeadArmour());
    private double myHp;

    // Enemy GUI
    @FXML private GridPane enemyGrid;
    @FXML private ImageView enemyBodyImage;
    @FXML private ImageView enemyHeadImage;
    @FXML private ImageView enemyWeaponImage;
    @FXML private ImageView enemyCross;
    @FXML private ProgressBar enemyArmourProgressBar;
    @FXML private ProgressBar enemyHeadProgressBar;
    @FXML private ProgressBar enemyHpBar;

    private Weapon enemyWeapon = randomWeaponGenerator();
    private Armour enemyArmour = randomArmourGenerator();
    private Head enemyHead = randomHeadGenerator();
    private double enemyHp;
    private double enemyArmourDurability = 100.0;
    private double enemyHeadDurability = 100.0;

    private boolean myHeadProtection;
    private boolean myBodyProtection;

    private boolean enemyBodyProtection = true;
    private boolean enemyHeadProtection = true;

    OptionAnimation optionAnimation = new OptionAnimation();
    OptionAnimation optionAnimation2 = new OptionAnimation();

    // ---------- Hover ----------
    public void enemyWeaponHover(){
        weaponInfo(enemyWeaponImage, enemyWeapon);
    }
    public void myWeaponHover(){
        weaponInfo(myWeaponImage, myWeapon);
    }
    public void enemyHpBarHover(){
        optionAnimation2.smoothScale(enemyCross);
        progressBarInfo(enemyHpBar, "Health");
    }
    public void myHpBarHover(){
        optionAnimation2.smoothScale(myCross);
        progressBarInfo(myHpBar, "Health");
    }
    public void enemyArmourProgressBarHover(){
        progressBarInfo(enemyArmourProgressBar, "Durability");
    }
    public void enemyHeadProgressBarHover(){
        progressBarInfo(enemyHeadProgressBar, "Durability");
    }
    public void myMyHeadProgressBarHover(){
        progressBarInfo(myHeadProgressBar, "Durability");
    }
    public void myMyArmourProgressBarHover(){
        progressBarInfo(myArmourProgressBar, "Durability");
    }
    public void myBodyHover(){
        bodyInfo(myBodyImage, myArmour, myBodyProtection, Items.getBodyArmourDurability());
    }
    public void enemyBodyHover(){
        bodyInfo(enemyBodyImage, enemyArmour, enemyBodyProtection, enemyArmourDurability);
    }
    public void enemyHeadHover(){
        headInfo(enemyHeadImage, enemyHead, enemyHeadProtection, enemyHeadDurability);
    }
    public void myHeadHover(){
        headInfo(myHeadImage,myHead, myHeadProtection, Items.getBodyArmourDurability());
    }
    public void bodyAttackHover(){
        optionAnimation.smoothScaleImage(bodyAttackIcon,
                GameGui.BODY_SHOT_GREEN.getTexture(),
                GameGui.BODY_SHOT.getTexture());
        titleGreen("Aim Body");
        itemInfoLabel.setText("Hit chance: " + myWeapon.getHitChanceBody() + "%");
    }
    public void headAttackHover(){
        if(myWeapon == Weapon.FLAMETHROWER){
            optionAnimation.smoothScaleImage(headAttackIcon,
                    GameGui.BURN_ORANGE.getTexture(),
                    GameGui.BURN.getTexture());
            itemInfoLabel.setText("Burn chance: " + myWeapon.getHitChanceBody() + "%");
            titleGreen("BURN!");
        } else {
            optionAnimation.smoothScaleImage(headAttackIcon,
                    GameGui.HEAD_SHOT_GREEN.getTexture(),
                    GameGui.HEAD_SHOT.getTexture());
            titleGreen("Aim Head");
            itemInfoLabel.setText("Hit chance: " + myWeapon.getHitChanceHead() + "%");
        }
    }
    public void surrenderHover(){
        optionAnimation.smoothScaleImage(surrenderIcon,
                GameGui.SURRENDER_RED.getTexture(),
                GameGui.SURRENDER.getTexture());
        EntranceAnimation.fadeInAnimation(mainTitle, 1000);
        mainTitle.setTextFill(Color.RED);
        mainTitle.setText("SURRENDER");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setProtection();
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        SmoothMoveAnimation.smoothAnimation(myLogLabel);
        SmoothMoveAnimation.smoothAnimation(enemyLogLabel);
        SmoothMoveAnimation.smoothAnimation(itemInfoLabel);
        myHp = 100.0;
        enemyHp = 100.0;
        System.out.println("Experience: " + Items.getExperience());
        System.out.println("enemy armour: " + enemyArmour.ordinal());
        System.out.println("enemy head: " + enemyHead.ordinal());
        System.out.println("enemy weapon: " + enemyWeapon.ordinal());

        myBodyImage.setImage(myArmour.getTexture());
        myHeadImage.setImage(myHead.getTexture());
        myWeaponImage.setImage(myWeapon.getTexture());

        enemyBodyImage.setImage(enemyArmour.getTexture());
        enemyHeadImage.setImage(enemyHead.getTexture());
        enemyWeaponImage.setImage(enemyWeapon.getTexture());
        if(myWeapon == Weapon.FLAMETHROWER){
            headAttackIcon.setImage(GameGui.BURN.getTexture());
            bodyAttackIcon.setVisible(false);
        } else{
            headAttackIcon.setImage(GameGui.HEAD_SHOT.getTexture());
            bodyAttackIcon.setImage(GameGui.BODY_SHOT.getTexture());
        }
        myArmourProgressBar.setProgress(Items.getBodyArmourDurability() / 100.0);
        myHeadProgressBar.setProgress(Items.getHeadArmourDurability() / 100.0);

        enemyHpBar.setStyle("-fx-accent: red");
        enemyArmourProgressBar.setProgress(enemyArmourDurability / 100.0);
        enemyHeadProgressBar.setProgress(enemyHeadDurability / 100.0);

        myHpBar.setStyle("-fx-accent: red");
        enemyHpBar.setProgress(enemyHp / 100.0);
        myHpBar.setProgress(myHp / 100.0);
    }
    private Weapon randomWeaponGenerator(){
        Random rand = new Random();
        if(Items.getExperience() <= 10){
            return ItemsSelector.getWeapon(rand.nextInt(1));
        } else if(Items.getExperience() <= 20){
            return ItemsSelector.getWeapon(rand.nextInt(3));
        } else if(Items.getExperience() <= 30){
            return ItemsSelector.getWeapon(rand.nextInt(3) + 2);
        } else{
            return ItemsSelector.getWeapon(rand.nextInt(2) + 3);
        }
    }
    private Armour randomArmourGenerator(){
        Random rand = new Random();
        if(Items.getExperience() <= 10){
            return ItemsSelector.getBodyArmour(rand.nextInt(1));
        } else if(Items.getExperience() <= 20){
            return ItemsSelector.getBodyArmour(rand.nextInt(2));
        } else if(Items.getExperience() <= 30){
            return ItemsSelector.getBodyArmour(rand.nextInt(3));
        } else{
            return ItemsSelector.getBodyArmour(rand.nextInt(2) + 2);
        }
    }
    private Head randomHeadGenerator(){
        Random rand = new Random();
        if(Items.getExperience() <= 10){
            return ItemsSelector.getHeadArmour(rand.nextInt(1));
        } else if(Items.getExperience() <= 20){
            return ItemsSelector.getHeadArmour(rand.nextInt(2));
        } else if(Items.getExperience() <= 30){
            return ItemsSelector.getHeadArmour(rand.nextInt(3));
        } else{
            return ItemsSelector.getHeadArmour(rand.nextInt(2) + 2);
        }
    }
    private void progressBarInfo(ProgressBar progressBar, String type){
        optionAnimation.smoothScale(progressBar);
        itemInfoLabel.setText(type + ": " + (int)(progressBar.getProgress() * 100) + "%");
    }
    private void weaponInfo(ImageView image, Weapon weapon){
        optionAnimation.smoothScaleImage(image,
                weapon.getTexture(),
                weapon.getTexture());
        itemInfoLabel.setText(weapon.getName() +
                "\n Damage body: " + weapon.getDamageBody() +
                "\n Damage head: " + weapon.getDamageHead());
    }
    private void bodyInfo(ImageView image, Armour armour, boolean protect,double percent){
        optionAnimation.smoothScaleImage(image,
                armour.getTexture(),
                armour.getTexture());
        if(protect){
            itemInfoLabel.setText(armour.getName() +
                    "\n Protection: " + armour.getAbsorption() + "%" +
                    "\n Durability: " + (int)(percent) + "%");
        } else {
            itemInfoLabel.setText(armour.getName() +
                    "\n Protection: " + "0" + "%" +
                    "\n Durability: " + "0" + "%");
        }
    }
    private void headInfo(ImageView image, Head head, boolean protect,double percent){
        optionAnimation.smoothScaleImage(image,
                head.getTexture(),
                head.getTexture());
        if(protect){
            itemInfoLabel.setText(head.getName() +
                    "\n Protection: " + head.getAbsorption() + "%" +
                    "\n Durability: " + (int)(percent) + "%");
        } else {
            itemInfoLabel.setText(head.getName() +
                    "\n Protection: " + "0" + "%" +
                    "\n Durability: " + "0" + "%");
        }
    }
    private void setProtection(){
        myHeadProtection = !(Items.getHeadArmourDurability() <= 0);
        myBodyProtection = !(Items.getBodyArmourDurability() <= 0);
    }
    private void titleGreen(String text){
        mainTitle.setTextFill(Color.GREEN);
        mainTitle.setText(text);
    }
}
