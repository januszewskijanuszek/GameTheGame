package service;

import enums.GameGui;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.animation.OptionAnimation;
import service.helper.Items;
import service.helper.WinHandler;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ResultController implements Initializable {
    @FXML private Label resultLabel;
    @FXML private ImageView resultImage;
    @FXML private ImageView yesImage;

    OptionAnimation optionAnimation = new OptionAnimation();
    // --------------- On Click ------------
    public void clickYes(MouseEvent event) throws IOException {
        Items.setExperience(Items.getExperience() + 1);
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
    // -------------- Hover -------------
    public void yesHover(){
        optionAnimation.smoothScaleImage(yesImage,
                Usable.APPLY_GREEN.getTexture(),
                Usable.APPLY.getTexture());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(WinHandler.isWon()){
            if(!WinHandler.isSurrender()){
                resultImage.setImage(GameGui.WIN.getTexture());
                resultLabel.setTextFill(Color.GREEN);
                resultLabel.setText("You Won " + (Items.getExperience() + 1) + " Battle!");
            } else{
                resultImage.setImage(GameGui.SURRENDER_ICON.getTexture());
                resultLabel.setTextFill(Color.ORANGE);
                resultLabel.setText("You are wounded!");
            }
        } else {
            resultImage.setImage(GameGui.DEATH.getTexture());
            Items.vanish();
            resultLabel.setTextFill(Color.RED);
            resultLabel.setText("You have lost!");
        }
    }
}
