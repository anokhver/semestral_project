package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static cz.cvut.anokhver.additional.FileManagement.createFolderIfNotExists;
import static cz.cvut.anokhver.additional.FileManagement.createProperPath;

public class GameLauncher extends Application {
    public static Logger log;

    public static void main(String[] args) {

        createFolderIfNotExists("logger");
        createFolderIfNotExists("saves");
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

        Configuration.init(createProperPath("config.json"));
        log.info("Launching game");
        //setting the main stage

        primaryStage.setTitle(Configuration.getWindowName());
        primaryStage.setResizable(false);

        //InputStream iconStream = GameLauncher.class.getResourceAsStream(Configuration.getPathIcon());
        //System.out.println(Configuration.getPathIcon());
        //System.out.println(iconStream);
        //primaryStage.getIcons().add(new Image(iconStream));

        primaryStage.getIcons().add(new Image(new File(createProperPath(Configuration.getPathIcon())).toURI().toString()));

        new GameLogic(primaryStage);
    }


}
