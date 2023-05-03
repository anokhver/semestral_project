package cz.cvut.anokhver;

import cz.cvut.anokhver.contollers.Contoller;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.level.Level;


import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.Dictionary;
import java.util.Hashtable;
import javafx.scene.layout.Pane;

public class GameLogic {

    private static Stage stage;
    private static final Dictionary<String, Contoller> controllers = new Hashtable<String, Contoller>();
    private Contoller state;
    public GameLogic(Stage primaryStage){
        GameLogic.stage = primaryStage;

        controllers.put("MainMenu", new MainMenuController());
        //controllers.put("GameMenu", new GameMenuController());

        state = controllers.get("MainMenu");
        stage.setScene(state.getView().getScene());

        //setMainMenu();
        stage.show();
    }

    public void setMainMenu(){
        state = controllers.get("MainMenu");
        stage.setScene(state.getView().getScene());
    }

    public static void new_game(){
        Configuration.init("config.json");
        Scene currentScene = stage.getScene();

        // If there is a current scene, hide it before setting the new scene
        if (currentScene != null) {
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.hide();
        }

        Level first_level = new Level(1);
        Canvas canvas = new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight());
        first_level.drawTileMap(canvas);
        Pane pane = new Pane(canvas);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        // Show the Stage
        stage.show();

    }
    public static void load_game(){
        System.out.println("LOADING GAME");

    }


}
