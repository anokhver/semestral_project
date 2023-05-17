package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;



public class GameLauncher extends Application {
    public static Logger log;

    public static void main(String[] args) {

        //setting up the logger
        try {
            LogManager.getLogManager().readConfiguration(
                    GameLauncher.class.getResourceAsStream("/logger/logging.properties"));
            log = Logger.getLogger(GameLauncher.class.getName());
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e);
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Configuration.init(create_proper_path("config.json"));
        log.info("Launching game");
        //setting the main stage

        primaryStage.setTitle(Configuration.getWindowName());
        primaryStage.setResizable(false);

        primaryStage.getIcons().add(new Image(new File(create_proper_path(Configuration.getPathIcon())).toURI().toString()));

        new GameLogic(primaryStage);
    }


}
