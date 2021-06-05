package service;

import enums.Armour;
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

public class BodyArmourShopController implements Initializable {
    @FXML private GridPane mainGrid;

    @FXML private Label tier1Description;
    @FXML private Label tier2Description;
    @FXML private Label tier3Description;
    @FXML private Label moneyLabel;
    @FXML private Label mainTitle;
    @FXML private Label bottomLabel;

    @FXML private ImageView armourTier1;
    @FXML private ImageView armourTier2;
    @FXML private ImageView armourTier3;
    @FXML private ImageView moneyImage;
    @FXML private ImageView exitImage;
    @FXML private ImageView imageShifter;

    OptionAnimation optionAnimation = new OptionAnimation();
    OptionAnimation optionAnimation2 = new OptionAnimation();
    // ----------- On Click ----------
    public void clickTier3(){
        checkAffordable(Armour.HEAVY_VEST);
    }
    public void clickTier2(){
        checkAffordable(Armour.VEST);
    }
    public void clickTier1(){
        checkAffordable(Armour.LIGHT_VEST);
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
    // ----------- Hover ----------
    public void armourTier3Hover(){
        optionAnimation.smoothScaleImage(armourTier3,
                Armour.HEAVY_VEST.getTexture(),
                Armour.HEAVY_VEST.getTexture());
        optionAnimation2.smoothScale(tier3Description);
        imageShifter.setImage(Armour.HEAVY_VEST.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Armour.HEAVY_VEST.getName() + "for " + Armour.HEAVY_VEST.getCost() + "$");
    }
    public void armourTier2Hover(){
        optionAnimation.smoothScaleImage(armourTier2,
                Armour.VEST.getTexture(),
                Armour.VEST.getTexture());
        optionAnimation2.smoothScale(tier2Description);
        imageShifter.setImage(Armour.VEST.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Armour.VEST.getName() + "for " + Armour.VEST.getCost() + "$");
    }
    public void armourTier1Hover(){
        optionAnimation.smoothScaleImage(armourTier1,
                Armour.LIGHT_VEST.getTexture(),
                Armour.LIGHT_VEST.getTexture());
        optionAnimation2.smoothScale(tier1Description);
        imageShifter.setImage(Armour.LIGHT_VEST.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Armour.LIGHT_VEST.getName() + "for " + Armour.LIGHT_VEST.getCost() + "$");
    }
    public void moneyHover(){
        optionAnimation.smoothScaleImage(moneyImage,
                Usable.COIN_YELLOW.getTexture(),
                Usable.COIN.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Current Money");
    }
    public void exitHover(){
        optionAnimation.smoothScaleImage(exitImage,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("EXIT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageShifter.setOpacity(0.3);
        imageShifter.setRotate(30);
        setDescription(tier1Description, Armour.LIGHT_VEST);
        setDescription(tier2Description, Armour.VEST);
        setDescription(tier3Description, Armour.HEAVY_VEST);
        EntranceAnimation.fadeInAnimation(mainGrid, 2000);
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        SmoothMoveAnimation.smoothAnimation(imageShifter);
        SmoothMoveAnimation.smoothAnimation(moneyLabel);
        SmoothMoveAnimation.smoothAnimation(moneyImage);
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        SmoothMoveAnimation.smoothAnimation(bottomLabel);
        SmoothMoveAnimation.smoothAnimation(tier1Description);
        SmoothMoveAnimation.smoothAnimation(tier2Description);
        SmoothMoveAnimation.smoothAnimation(tier3Description);
        moneyLabel.setText("" + Items.getMoney());
    }
    private void checkAffordable(Armour armour){
        if(armour.getCost() > Items.getMoney()){
            mainTitle.setTextFill(Color.RED);
            mainTitle.setText("You can't afford!");
        } else if(Items.getBodyArmour() >= armour.ordinal()){
            mainTitle.setTextFill(Color.ORANGE);
            mainTitle.setText("You have better armour!");
        } else {
            Items.setBodyArmour(armour.ordinal());
            mainTitle.setTextFill(Color.GREEN);
            mainTitle.setText("Thanks for purchase!");
            Items.setBodyArmour(armour.ordinal());
            Items.setBodyArmourDurability(100.0);
            Items.setMoney(Items.getMoney() - armour.getCost());
            moneyLabel.setText("" + Items.getMoney());
        }
    }
    private void setDescription(Label label, Armour armour){
        label.setText(armour.getName() +
                "\n" + "Durability: " + armour.getDurability() +
                "\n" + "Absorption: " + armour.getAbsorption() +
                "\n" + "Price: " + armour.getCost() + "$");
    }
}
