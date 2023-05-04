package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

public class GameLauncher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Configuration.init(create_proper_path("config.json"));

        //setting the stage and scene
        primaryStage.setTitle(Configuration.getWindowName());
        primaryStage.setResizable(false);



        String dir = create_proper_path( Configuration.getPathIcon());
        File icon = new File(dir);

        primaryStage.getIcons().add(new Image(icon.toURI().toString()));

        GameLogic game = new GameLogic(primaryStage);
    }
}
