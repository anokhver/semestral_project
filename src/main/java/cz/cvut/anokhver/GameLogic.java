package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.contollers.Contoller;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.level.Level;


import cz.cvut.anokhver.movement.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.Dictionary;
import java.util.Hashtable;

import javafx.scene.layout.Pane;

public class GameLogic {


    //STAGE + MENU
    private static Stage stage;
    private static final Dictionary<String, Contoller> controllers = new Hashtable<String, Contoller>();

    //THE GAME PARAMETERS
    private static Contoller cur_state;

    private static Level cur_level;
    private static final Player hero = new Player();


    public GameLogic(Stage primaryStage){
        GameLogic.stage = primaryStage;

        controllers.put("MainMenu", new MainMenuController());
        cur_state = controllers.get("MainMenu");

        setMainMenu();
        stage.show();
    }

    public void setMainMenu(){
        cur_state = controllers.get("MainMenu");
        stage.setScene(cur_state.getView().getScene());
    }

    public static void new_game(){
        Configuration.init("config.json");
        Scene currentScene = stage.getScene();

        // If there is a current scene, hide it before setting the new scene
        if (currentScene != null) {
            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.hide();
        }

        //drawing first level
        cur_level = new Level(1);
        Canvas cur_canvas = new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight());
        cur_level.drawTileMap(cur_canvas);
        cur_level.drawPlayer(cur_canvas, hero);

        //setting the canvas
        Pane pane = new Pane(cur_canvas);
        currentScene = new Scene(pane);

        stage.setScene(currentScene);
        // Show the Stage
        stage.show();

    }
    public static void load_game(){
        System.out.println("LOADING GAME");

    }

    public static void handleKeyPressed(KeyCode code) {
        switch (code) {
            case W:
                hero.move(Direction.TOP);
                break;
            case A:
                hero.move(Direction.LEFT);
                break;
            case S:
                hero.move(Direction.BOTTOM);
                break;
            case D:
                hero.move(Direction.RIGHT);
                break;
        }
    }


}
