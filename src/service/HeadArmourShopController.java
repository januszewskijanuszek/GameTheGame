package service;
/**
 * @author Patryk Januszewski
 */
import enums.Head;
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

public class HeadArmourShopController implements Initializable {
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
    public void clickTier3(MouseEvent event){
        checkAffordable(Head.FLANK);
    }
    public void clickTier2(MouseEvent event){
        checkAffordable(Head.GERMAN_HELMET);
    }
    public void clickTier1(MouseEvent event){
        checkAffordable(Head.STEEL_HELMET);
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
    public void armourTier3Hover(MouseEvent event){
        optionAnimation.smoothScaleImage(armourTier3,
                Head.FLANK.getTexture(),
                Head.FLANK.getTexture());
        optionAnimation2.smoothScale(tier3Description);
        imageShifter.setImage(Head.FLANK.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Head.FLANK.getName() + "for " + Head.FLANK.getCost() + "$");
    }
    public void armourTier2Hover(MouseEvent event){
        optionAnimation.smoothScaleImage(armourTier2,
                Head.GERMAN_HELMET.getTexture(),
                Head.GERMAN_HELMET.getTexture());
        optionAnimation2.smoothScale(tier2Description);
        imageShifter.setImage(Head.GERMAN_HELMET.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Head.GERMAN_HELMET.getName() + "for " + Head.GERMAN_HELMET.getCost() + "$");
    }
    public void armourTier1Hover(MouseEvent event){
        optionAnimation.smoothScaleImage(armourTier1,
                Head.STEEL_HELMET.getTexture(),
                Head.STEEL_HELMET.getTexture());
        optionAnimation2.smoothScale(tier1Description);
        imageShifter.setImage(Head.STEEL_HELMET.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Buy " + Head.STEEL_HELMET.getName() + "for " + Head.STEEL_HELMET.getCost() + "$");
    }
    public void moneyHover(MouseEvent event){
        optionAnimation.smoothScaleImage(moneyImage,
                Usable.COIN_YELLOW.getTexture(),
                Usable.COIN.getTexture());
        EntranceAnimation.fadeInAnimation(bottomLabel, 1000);
        bottomLabel.setText("Current Money");
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
        imageShifter.setOpacity(0.3);
        imageShifter.setRotate(30);
        setDescription(tier1Description, Head.STEEL_HELMET);
        setDescription(tier2Description, Head.GERMAN_HELMET);
        setDescription(tier3Description, Head.FLANK);
        EntranceAnimation.fadeInAnimation(mainGrid, 2000);
        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        SmoothMoveAnimation.smoothAnimation(imageShifter);
        SmoothMoveAnimation.smoothAnimation(moneyLabel);
        SmoothMoveAnimation.smoothAnimation(moneyImage);
        SmoothMoveAnimation.smoothAnimation(mainTitle);
        SmoothMoveAnimation.smoothAnimation(bottomLabel);
        // SmoothMoveAnimation.smoothAnimation(tier1Description);
        // SmoothMoveAnimation.smoothAnimation(tier2Description);
        // SmoothMoveAnimation.smoothAnimation(tier3Description);
        moneyLabel.setText("" + Items.getMoney());
    }
    private void checkAffordable(Head head){
        if(head.getCost() > Items.getMoney()){
            mainTitle.setTextFill(Color.RED);
            mainTitle.setText("You can't afford!");
        } else if(Items.getHeadArmour() >= head.ordinal()){
            mainTitle.setTextFill(Color.ORANGE);
            mainTitle.setText("You have better armour!");
        } else {
            Items.setHeadArmour(head.ordinal());
            mainTitle.setTextFill(Color.GREEN);
            mainTitle.setText("Thanks for purchase!");
            Items.setHeadArmour(head.ordinal());
            Items.setHeadArmourDurability(100.0);
            Items.setMoney(Items.getMoney() - head.getCost());
            moneyLabel.setText("" + Items.getMoney());
        }
    }
    private void setDescription(Label label, Head head){
        label.setText(head.getName() +
                "\n" + "Durability: " + head.getDurability() +
                "\n" + "Absorption: " + head.getAbsorption() +
                "\n" + "Price: " + head.getCost() + "$");
    }
}