package service;

import enums.Armour;
import enums.Head;
import enums.Usable;
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

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RepairShopController implements Initializable {
    @FXML private GridPane mainGrid;

    @FXML private Label mainTitle;
    @FXML private Label infoLabel;
    @FXML private Label bodyProgressBarInfo;
    @FXML private Label headProgressBarInfo;

    @FXML private ImageView armourImage;
    @FXML private ImageView headImage;
    @FXML private ImageView imageShifter;
    @FXML private ImageView exitImage;

    @FXML private ProgressBar progressBarHead;
    @FXML private ProgressBar progressBarBody;

    Armour armour = ItemsSelector.getBodyArmour(Items.getBodyArmour());
    Head head = ItemsSelector.getHeadArmour(Items.getHeadArmour());

    OptionAnimation optionAnimation = new OptionAnimation();
    // ------------ On Click ----------
    public void clickArmour(MouseEvent event){
        if((int) (((100.0 - Items.getBodyArmourDurability())) / 100 * armour.getCost()) > Items.getMoney()){
            mainTitle.setTextFill(Color.RED);
            mainTitle.setText("You can't afford!");
        } else if(Items.getBodyArmourDurability() == 100.0){
            mainTitle.setTextFill(Color.ORANGE);
            mainTitle.setText("Item is fully repaired!");
        } else {
            Items.setMoney(Items.getMoney() - (int) (((100.0 - Items.getBodyArmourDurability())) / 100 * armour.getCost()));
            Items.setBodyArmourDurability(100.0);
            mainTitle.setTextFill(Color.GREEN);
            mainTitle.setText("Item Repaired!");
            bodyProgressBarInfo.setText(Items.getBodyArmourDurability() + "%");
            progressBarBody.setProgress(Items.getBodyArmourDurability()/100);
        }
    }
    public void clickHead(MouseEvent event){
        if((int) (((100.0 - Items.getHeadArmourDurability())) / 100 * head.getCost()) > Items.getMoney()){
            mainTitle.setTextFill(Color.RED);
            mainTitle.setText("You can't afford!");
        } else if(Items.getHeadArmourDurability() == 100.0){
            mainTitle.setTextFill(Color.ORANGE);
            mainTitle.setText("Item is fully repaired!");
        } else {
            Items.setMoney(Items.getMoney() - (int) (((100.0 - Items.getHeadArmourDurability())) / 100 * head.getCost()));
            Items.setHeadArmourDurability(100.0);
            mainTitle.setTextFill(Color.GREEN);
            mainTitle.setText("Item Repaired!");
            headProgressBarInfo.setText(Items.getHeadArmourDurability() + "%");
            progressBarHead.setProgress(Items.getHeadArmourDurability()/100);
        }
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
    // ------------ Hover ----------
    public void bodyHover(MouseEvent event){
        optionAnimation.smoothScaleImage(armourImage,
                armour.getTexture(),
                armour.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        infoLabel.setText(armour.getName() +
                "\n Durability: " + armour.getDurability() + "/"+
                (int) ((Items.getBodyArmourDurability()/100) * armour.getDurability())+
                "\n Percent: " + Items.getBodyArmourDurability() + "%" +
                "\n Cost of repair: " + (int) (((100.0 - Items.getBodyArmourDurability())) / 100 * armour.getCost()) + "$");
        imageShifter.setImage(armour.getTexture());
    }
    public void headHover(MouseEvent event){
        optionAnimation.smoothScaleImage(headImage,
                head.getTexture(),
                head.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        infoLabel.setText(head.getName() +
                "\n Durability: " + head.getDurability() + "/"+
                (int) ((Items.getHeadArmourDurability()/100) * head.getDurability())+
                "\n Percent: " + Items.getHeadArmourDurability() + "%" +
                "\n Cost of repair: " + (int) (((100.0 - Items.getHeadArmourDurability())) / 100 * head.getCost()) + "$");
        imageShifter.setImage(head.getTexture());
    }

    public void bodyProgressBarInfoHover(MouseEvent event){
        progressBarBodyHover(event);
    }
    public void headProgressBarInfoHover(MouseEvent event){
        progressBarHeadHover(event);
    }
    public void progressBarBodyHover(MouseEvent event){
        optionAnimation.smoothsScaleProgressBar(progressBarBody, bodyProgressBarInfo, Color.GRAY);
    }
    public void progressBarHeadHover(MouseEvent event){
        optionAnimation.smoothsScaleProgressBar(progressBarHead, headProgressBarInfo, Color.GRAY);
    }
    public void exitHover(MouseEvent event){
        optionAnimation.smoothScaleImage(exitImage,
                Usable.EXIT_DOOR_RED.getTexture(),
                Usable.EXIT_DOOR.getTexture());
        EntranceAnimation.fadeInAnimation(infoLabel, 1000);
        infoLabel.setText("EXIT");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoLabel.setText("");
        imageShifter.setOpacity(0.3);
        imageShifter.setRotate(30);
        imageShifter.setScaleX(2);
        imageShifter.setScaleY(2);
        bodyProgressBarInfo.setText(Items.getBodyArmourDurability() + "%");
        headProgressBarInfo.setText(Items.getHeadArmourDurability() + "%");
        bodyProgressBarInfo.setVisible(false);
        headProgressBarInfo.setVisible(false);
        progressBarBody.setProgress(Items.getBodyArmourDurability()/100);
        progressBarHead.setProgress(Items.getHeadArmourDurability()/100);

        SmoothMoveAnimation.smoothAnimation(mainTitle);
        SmoothMoveAnimation.smoothAnimation(infoLabel);

        EntranceAnimation.fadeInAnimation(mainTitle, 2000);
        EntranceAnimation.fadeInAnimation(headImage, 2000);
        EntranceAnimation.fadeInAnimation(armourImage, 2000);
        EntranceAnimation.fadeInAnimation(progressBarHead, 2000);
        EntranceAnimation.fadeInAnimation(progressBarBody, 2000);
        EntranceAnimation.fadeInAnimation(bodyProgressBarInfo, 2000);
        EntranceAnimation.fadeInAnimation(headProgressBarInfo, 2000);
        armourImage.setImage(armour.getTexture());
        headImage.setImage(head.getTexture());
    }
}
