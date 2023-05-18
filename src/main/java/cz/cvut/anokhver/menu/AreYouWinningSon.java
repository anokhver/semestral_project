package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

public class AreYouWinningSon extends Scene {

    public AreYouWinningSon(String end)
    {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());
        Image image = new Image("file:" + File.separator + createProperPath("src/main/resources/additional/" + end +".png"), Configuration.getWindowWidth(), Configuration.getWindowHeight(), false, false);

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Create a StackPane layout and add the ImageView to it
        Pane root = (Pane) this.getRoot();
        root.getChildren().add(imageView);
        setOnKeyPressed(event -> GameLogic.setMainMenu());
    }


}
