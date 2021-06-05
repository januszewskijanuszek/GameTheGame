package service;

import enums.Armour;
import enums.Head;
import enums.Usable;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.animation.OptionAnimation;
import service.animation.SmoothMoveAnimation;
import service.helper.Items;
import service.helper.ItemsSelector;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @FXML private Label mainTitle;
    @FXML private ImageView weaponImage;
    @FXML private ImageView bodyArmourImage;
    @FXML private ImageView headArmourImage;
    @FXML private ImageView exitImage;

    @FXML private ProgressBar bodyArmourDurability;
    @FXML private ProgressBar headArmourDurability;

    @FXML private Label weaponDescription;
    @FXML private Label bodyArmourDescription;
    @FXML private Label headArmourDescription;

    @FXML private Label percentBody;
    @FXML private Label percentHead;

    OptionAnimation optionAnimation = new OptionAnimation();

    private final Weapon weapon = ItemsSelector.getWeapon(Items.getWeapon());
    private final Armour bodyArmour = ItemsSelector.getBodyArmour(Items.getBodyArmour());
    private final Head headArmour = ItemsSelector.getHeadArmour(Items.getHeadArmour());

    // ----------- On Click ----------
    public void clickExit(MouseEvent event) throws IOException {
        System.out.println("Switching to main Menu");
        // Switch between window
        String css1 = Objects.requireNonNull(this.getClass().getResource("../style/style1.css")).toExternalForm(); // Setting css file
        Parent rootMain = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../scenes/Game.fxml")));
        Stage stageMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene sceneMain = new Scene(rootMain);
        sceneMain.getStylesheets().add(css1);
        stageMain.setScene(sceneMain);
        stageMain.show();
    }

    //  --------- Hover ----------
    public void exitHover(){
        optionAnimation.smoothScaleImage(exitImage,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
    }
    public void percentBodyHover(MouseEvent event){
        bodyArmourDurabilityHover(event);
    }
    public void percentHeadHover(MouseEvent event){
        headArmourDurabilityHover(event);
    }
    public void bodyArmourDurabilityHover(MouseEvent event){
        optionAnimation.smoothsScaleProgressBar(bodyArmourDurability, percentBody, Color.GRAY);
    }
    public void headArmourDurabilityHover(MouseEvent event){
        optionAnimation.smoothsScaleProgressBar(headArmourDurability, percentHead, Color.GRAY);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SmoothMoveAnimation.smoothAnimation(mainTitle);

        bodyArmourDurability.setProgress(Items.getBodyArmourDurability()/100);
        headArmourDurability.setProgress(Items.getHeadArmourDurability()/100);

        percentBody.setText(Items.getBodyArmourDurability() + "%");
        percentHead.setText(Items.getHeadArmourDurability() + "%");
        percentBody.setVisible(false);
        percentHead.setVisible(false);

        String repairBody = "";
        String repairHead = "";
        if(Items.getBodyArmourDurability() <= 20){
            repairBody = "Repair Required!";
        }
        if(Items.getHeadArmourDurability() <= 20){
            repairHead = "Repair Required!";
        }

        // Setting Weapon statistic
        weaponDescription.setText(weapon.getName() +
                "\n Damage body: " + weapon.getDamageBody() +
                "\n Damage head: " + weapon.getDamageHead() +
                "\n Head hit chance: " + weapon.getHitChanceHead() + "%" +
                "\n Body hit chance: " + weapon.getHitChanceBody() + "%");
        bodyArmourDescription.setText(bodyArmour.getName() +
                "\n Durability: " + bodyArmour.getDurability() + "/" +
                (int) ((Items.getBodyArmourDurability()/100)*bodyArmour.getDurability()) +
                "\n Absorption: " + bodyArmour.getAbsorption() + "%" +
                "\n" + repairBody);
        headArmourDescription.setText(headArmour.getName() +
                "\n Durability: " + headArmour.getDurability() + "/" +
                (int) ((Items.getHeadArmourDurability()/100)*headArmour.getDurability()) +
                "\n Absorption: " + headArmour.getAbsorption() + "%" +
                "\n" + repairHead);
        SmoothMoveAnimation.smoothAnimation(weaponDescription);
        SmoothMoveAnimation.smoothAnimation(bodyArmourDescription);
        SmoothMoveAnimation.smoothAnimation(headArmourDescription);

        bodyArmourImage.setImage(bodyArmour.getTexture());
        headArmourImage.setImage(headArmour.getTexture());
        weaponImage.setImage(weapon.getTexture());
    }
}
