package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.contollers.Contoller;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.level.Coordinates;
import cz.cvut.anokhver.level.Level;


import cz.cvut.anokhver.level.SingleTile;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.util.Dictionary;
import java.util.Hashtable;
import javafx.scene.layout.Pane;

public class GameLogic {

    private static Stage stage;
    private static Canvas cur_canvas;

    private static final Dictionary<String, Contoller> controllers = new Hashtable<String, Contoller>();
    private Contoller state;

    private static Level cur_level;
    private static final Player hero = new Player();

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

        //drawing first level
        cur_level = new Level(1);
        cur_canvas = new Canvas(Configuration.getWindowWidth(), Configuration.getWindowHeight());
        cur_level.drawTileMap(cur_canvas);
        cur_level.drawPlayer(cur_canvas, hero);

        //setting the canvas
        Pane pane = new Pane(cur_canvas);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        // Show the Stage
        stage.show();


        //game_loop();

    }
    public static void load_game(){
        System.out.println("LOADING GAME");

    }

    private static void game_loop(){
        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // Calculate the time elapsed since the start of the game
                double elapsedTime = (currentNanoTime - startNanoTime) / 1000000000.0; // Convert to seconds

                // Update the game state
                update(elapsedTime);

                // Render the game screen
                render();
            }
        }.start();
    }

    private static void update(double elapsedTime) {
        // Update the game state based on the elapsed time and user input
        // For example, update the player position, check for collisions, etc.
    }

    private static void render() {
        cur_level.drawPlayer(cur_canvas, hero);
    }

}
