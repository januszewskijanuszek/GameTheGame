package service;

import enums.Armour;
import enums.GameGui;
import enums.Head;
import enums.Weapon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.animation.EntranceAnimation;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;
import service.helper.ItemsSelector;
import service.helper.WinHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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

    private final Weapon myWeapon = ItemsSelector.getWeapon(Items.getWeapon());
    private final Armour myArmour = ItemsSelector.getBodyArmour(Items.getBodyArmour());
    private final Head myHead = ItemsSelector.getHeadArmour(Items.getHeadArmour());
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

    private final Weapon enemyWeapon = randomWeaponGenerator();
    private final Armour enemyArmour = randomArmourGenerator();
    private final Head enemyHead = randomHeadGenerator();
    private double enemyHp;
    private double enemyArmourDurability = 100.0;
    private double enemyHeadDurability = 100.0;

    private boolean myHeadProtection;
    private boolean myBodyProtection;

    private boolean enemyBodyProtection = true;
    private boolean enemyHeadProtection = true;

    OptionAnimation optionAnimation = new OptionAnimation();
    OptionAnimation optionAnimation2 = new OptionAnimation();

    // ----------- On Click -----------
    public void clickSurrender(MouseEvent event) throws IOException {
        enemyAttack();
        WinHandler.setSurrender(true);
        WinHandler.setWon(true);
        warp(event);
    }
    public void clickBodyAttack(MouseEvent event) throws IOException {
        attack(false, myWeapon, true);
        isWon(event);
        enemyAttack();
        isWon(event);
    }
    public void clickHeadAttack(MouseEvent event) throws IOException {
        attack(false, myWeapon, false);
        isWon(event);
        enemyAttack();
        isWon(event);
    }
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

        EntranceAnimation.fadeInAnimation(myGrid, 2000);
        EntranceAnimation.fadeInAnimation(enemyGrid, 2000);

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
    // --------------------- Private functions -----------------------
    private void enemyAttack(){
        Random random = new Random();
        boolean target;
        target = random.nextInt(2) == 1;
        attack(true, enemyWeapon, target);
    }
    private void isWon(MouseEvent event) throws IOException {
        if(enemyHp <= 0){
            Items.setMoney(Items.getMoney() + 15);
            WinHandler.setSurrender(false);
            WinHandler.setWon(true);
            warp(event);
        }
        if(myHp <= 0){
            WinHandler.setWon(false);
            warp(event);
        }
    }
    private void warp(MouseEvent event) throws IOException {
        System.out.println("Switching to Result");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Result.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }
    private void attack(boolean me, Weapon weapon, boolean body){
        if(me){
            System.out.println("Attack body: " + body);
            attackMe(weapon, body);
        } else {
            System.out.println("Enemy Attack body: " + body);
            attackEnemy(weapon, body);
        }
    }
    private void attackEnemy(Weapon weapon, boolean body){
        Random random = new Random();
        int chance = random.nextInt(100) + 1;
        System.out.println("My chance: " + chance);
        if(body){
            System.out.println("My hit chance body: " + chance);
            if(weapon.getHitChanceBody() < chance){
                System.out.println("Missed!");
                myLogLabel.setTextFill(Color.RED);
                myLogLabel.setText("Missed hit body!");
            } else {
                System.out.println("Hit body!");
                myLogLabel.setTextFill(Color.GREEN);
                myLogLabel.setText("HIT BODY!");
                if(enemyBodyProtection){
                    System.out.println("Enemy hp" + enemyHp);
                    System.out.println("Result of pattern: " + (weapon.getDamageBody() - ((((double) enemyArmour.getAbsorption()) / 100)) * weapon.getDamageBody()));
                    enemyHp = enemyHp - (weapon.getDamageBody() - ((((double) enemyArmour.getAbsorption()) / 100)) * weapon.getDamageBody());
                    System.out.println("Enemy hp after " + enemyHp);
                    System.out.println("Enemy armour durability before: " + enemyArmourDurability);
                    enemyArmourDurability = enemyArmourDurability - (1.0 / enemyArmour.getDurability()) * 100;
                    System.out.println("Enemy armour durability after: " + enemyArmourDurability);
                    if(enemyArmourDurability <= 0) enemyBodyProtection = false;
                    System.out.println("Enemy body protection: " + enemyBodyProtection);
                } else {
                    System.out.println("Enemy body protection: " + false);
                    System.out.println("Enemy hp before: " + enemyHp);
                    enemyHp = enemyHp - weapon.getDamageBody();
                    System.out.println("Enemy hp after: " + enemyHp);
                }
                System.out.println("Enemy progress bar before: " + enemyHpBar.getProgress());
                enemyHpBar.setProgress(enemyHp / 100.0);
                System.out.println("Enemy progress bar after: " + enemyHpBar.getProgress());
            }
        } else {
            System.out.println("Hit chance head: " + chance);
            if(weapon.getHitChanceHead() < chance){
                System.out.println("Missed hit head!");
                myLogLabel.setTextFill(Color.RED);
                if(weapon == Weapon.FLAMETHROWER){
                    myLogLabel.setText("Missed Burn!");
                } else {
                    myLogLabel.setText("Missed hit head!");
                }
            } else {
                System.out.println("Head hit!");
                myLogLabel.setTextFill(Color.GREEN);
                if(weapon == Weapon.FLAMETHROWER){
                    myLogLabel.setText("BURN!");
                } else {
                    myLogLabel.setText("HIT HEAD!");
                }
                if(enemyHeadProtection){
                    System.out.println("Result of pattern: " + (weapon.getDamageHead() - (((((double) enemyHead.getAbsorption()) / 100)) * weapon.getDamageHead())));
                    System.out.println("Enemy hp before: " + enemyHp);
                    enemyHp = enemyHp  - (weapon.getDamageHead() - (((((double) enemyHead.getAbsorption()) / 100)) * weapon.getDamageHead()));
                    System.out.println("Enemy hp after: " + enemyHp);
                    System.out.println("Enemy head durability before: " + enemyHeadDurability);
                    enemyHeadDurability = enemyHeadDurability - (1.0 / enemyHead.getDurability()) * 100;
                    System.out.println("Enemy head durability after: " + enemyHeadDurability);
                    if(enemyHeadDurability <= 0) enemyHeadProtection = false;
                } else {
                    enemyHp = enemyHp - weapon.getDamageHead();
                }
                enemyHpBar.setProgress(enemyHp / 100.0);
            }
        }
        enemyHeadProgressBar.setProgress(enemyHeadDurability / 100.0);
        enemyArmourProgressBar.setProgress(enemyArmourDurability / 100.0);
    }
    private void attackMe(Weapon weapon, boolean body){
        Random random = new Random();
        int chance = random.nextInt(100) + 1;
        System.out.println("Enemy Chance: " + chance);
        if(body){
            System.out.println("Enemy chit chance body: " + weapon.getHitChanceBody());
            if(weapon.getHitChanceBody() < chance){
                System.out.println("Enemy Missed!");
                enemyLogLabel.setTextFill(Color.GREEN);
                enemyLogLabel.setText("Missed hit body!");
            } else{
                System.out.println("Enemy Hit body!");
                enemyLogLabel.setTextFill(Color.RED);
                enemyLogLabel.setText("HIT BODY !");
                if(myBodyProtection){
                    System.out.println("Current my HP: " + myHp);
                    System.out.println("Result of pattern: " + (weapon.getDamageBody() - ((((double) myArmour.getAbsorption()) / 100) * weapon.getDamageBody())));
                    myHp = myHp - (weapon.getDamageBody() - ((((double) myArmour.getAbsorption()) / 100) * weapon.getDamageBody()));
                    System.out.println("Hp after hit: " + myHp);
                    System.out.println("My armour durability: " + Items.getBodyArmourDurability());
                    Items.setBodyArmourDurability(Items.getBodyArmourDurability() - (1.0 / myArmour.getDurability()) * 100);
                    System.out.println("My armour durability after: " + Items.getBodyArmourDurability());
                    if(Items.getBodyArmourDurability() <= 0) myBodyProtection = false;
                    System.out.println("My armour protection: " + myBodyProtection);
                } else{
                    System.out.println("My armour protection: " + false);
                    System.out.println("Current my HP: " + myHp);
                    myHp = myHp - weapon.getDamageBody();
                    System.out.println("Hp after hit: " + myHp);
                }
                System.out.println("Hp progress bar: " + myHpBar.getProgress());
                myHpBar.setProgress(myHp / 100.0);
            }
        } else {
            System.out.println("Enemy chit chance head: " + weapon.getHitChanceHead());
            if(weapon.getHitChanceHead() < chance){
                System.out.println("Enemy Missed!");
                enemyLogLabel.setTextFill(Color.GREEN);
                enemyLogLabel.setText("Missed hit head!");
            } else{
                System.out.println("Enemy Hit head!");
                enemyLogLabel.setTextFill(Color.RED);
                enemyLogLabel.setText("HIT HEAD!");
                if(myHeadProtection){
                    System.out.println("Current my HP: " + myHp);
                    System.out.println("Result of pattern: " + (weapon.getDamageHead() - (((double) (myHead.getAbsorption()) / 100) * weapon.getDamageHead())));
                    myHp = myHp - (weapon.getDamageHead() - (((double) (myHead.getAbsorption()) / 100) * weapon.getDamageHead()));
                    System.out.println("Hp after hit: " + myHp);
                    System.out.println("My head durability: " + Items.getHeadArmourDurability());
                    Items.setHeadArmourDurability(Items.getHeadArmourDurability() - (1.0 / myHead.getDurability()) * 100);
                    if(Items.getHeadArmourDurability() <= 0) myHeadProtection = false;
                    System.out.println("My head protection: " + myHeadProtection);
                } else{
                    System.out.println("My armour protection: " + false);
                    System.out.println("Current my HP: " + myHp);
                    myHp = myHp - weapon.getDamageHead();
                    System.out.println("Hp after hit: " + myHp);
                }
                System.out.println("Hp progress bar: " + myHpBar.getProgress());
                myHpBar.setProgress(myHp / 100.0);
            }
        }
        myHeadProgressBar.setProgress(Items.getHeadArmourDurability() / 100.0);
        myArmourProgressBar.setProgress(Items.getBodyArmourDurability() / 100.0);
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
