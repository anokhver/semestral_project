package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.contollers.AContoller;
import cz.cvut.anokhver.contollers.Level;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;

import cz.cvut.anokhver.contollers.LevelHandler;
import cz.cvut.anokhver.movement.Coordinates;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Dictionary;
import java.util.Hashtable;

public class GameLogic {


    /**
     * STAGE + MENU
     */
    private static Stage stage;
    private static final Dictionary<String, AContoller> controllers = new Hashtable<String, AContoller>();

    public static AContoller getCur_state() {
        return cur_state;
    }

    /**
     * THE GAME PARAMETERS
     */
    private static AContoller cur_state;

    protected static LevelHandler cur_level;
    private static final Player hero = new Player();
    private static GameLoop gameLoop = new GameLoop();

    public GameLogic(Stage primaryStage){
        GameLogic.stage = primaryStage;
        hero.setPosition(new Coordinates(100,100));


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

        //drawing level
        cur_level = new LevelHandler(hero, new Level(1), stage);
        cur_level.draw_level_start();
        //starting game loop
        gameLoop.start();
    }
    public static void load_game(){
        System.out.println("LOADING GAME");

    }


}

class GameLoop extends AnimationTimer {
    private long lastNanoTime = System.nanoTime();

    @Override
    public void handle(long now) {
        double delta = (now - lastNanoTime) / 1000000000.0;

        // Frame rate cap
        if (delta > 1/1000.00) {
            lastNanoTime = now;
            GameLogic.cur_level.update(delta);
        }
    }
}
