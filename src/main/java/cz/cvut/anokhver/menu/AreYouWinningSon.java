package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.animation.PauseTransition;
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

        PauseTransition delay = new PauseTransition(Duration.seconds(5));

        this.setOnKeyPressed(event -> {
            delay.play();
            // Close the stage when any key is pressed
            GameLogic.setMainMenu();
        });


    }


}
