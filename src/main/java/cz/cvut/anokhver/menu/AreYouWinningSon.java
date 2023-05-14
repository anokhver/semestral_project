package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.util.Duration;
import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class AreYouWinningSon extends Scene {

    public AreYouWinningSon()
    {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());
        Image image = new Image("file:" + File.separator + create_proper_path("src/main/resources/additional/win.png"), Configuration.getWindowWidth(), Configuration.getWindowHeight(), false, false);

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Create a StackPane layout and add the ImageView to it
        Pane root = (Pane) this.getRoot();
        root.getChildren().add(imageView);


        this.setOnKeyPressed(keyevent -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> GameLogic.setMainMenu()));
            timeline.play();

        });


    }


}
