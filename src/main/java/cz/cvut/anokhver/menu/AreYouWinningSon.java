package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static cz.cvut.anokhver.additional.FileManagement.getFileFromResourceAsStream;

/**
 * Win & lose screen view
 *
 * @author Veronika
 */
public class AreYouWinningSon extends Scene {

    /**
     * Setting the win or lose screen after pushing any button it resets to the main menu
     *
     * @param end lose or win
     */
    public AreYouWinningSon(String end) {
        super(new Pane(), Configuration.getWindowWidth(), Configuration.getWindowHeight());

        Image image = new Image(getFileFromResourceAsStream("additional/" + end + ".png"), Configuration.getWindowWidth(), Configuration.getWindowHeight(), false, false);

        // Create an ImageView to display the image
        ImageView imageView = new ImageView(image);

        // Create a StackPane layout and add the ImageView to it
        Pane root = (Pane) this.getRoot();
        root.getChildren().add(imageView);
        setOnKeyPressed(event -> GameLogic.setMainMenu());
    }


}
