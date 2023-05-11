package cz.cvut.anokhver;

import cz.cvut.anokhver.contollers.AContoller;
import cz.cvut.anokhver.level.Level;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.contollers.LevelHandler;
import cz.cvut.anokhver.menu.AreYouWinningSon;
import cz.cvut.anokhver.movement.Coordinates;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.Dictionary;
import java.util.Hashtable;


public class GameLogic {

    //public static Logger log = Logger.getLogger(GameLogic.class.getName());

    /**
     * STAGE + MENU
     */
    private static Stage stage;
    private static final Dictionary<String, AContoller> controllers = new Hashtable<String, AContoller>();


    /**
     * THE GAME PARAMETERS
     */
    private static AContoller cur_state;

    protected static LevelHandler cur_level;
    private static final Player hero = new Player();
    private static GameLoop gameLoop = new GameLoop();

    public GameLogic(Stage primaryStage){
        GameLauncher.log.info("Setting up the logic");

        GameLogic.stage = primaryStage;
        //log.info("Setting coordinates to player 100, 100");
        hero.setPosition(new Coordinates(100,100));

        controllers.put("MainMenu", new MainMenuController());
        cur_state = controllers.get("MainMenu");
        setMainMenu();

        stage.show();
    }

    public static void setMainMenu(){
        GameLauncher.log.info("Open main menu");
        cur_state = controllers.get("MainMenu");
        cur_state.getView().getScene().setFill(Color.BLACK);
        stage.setScene(cur_state.getView().getScene());
    }

    public static void new_game(){
        GameLauncher.log.info("Start new game");
        stage.setScene(null);

        //creating and drawing
        cur_level = new LevelHandler(hero, new Level(1), stage);
        controllers.put("CurLevel", cur_level);

        cur_level.draw_level_start();
        //starting game loop
        gameLoop.start();
    }
    public static void load_game(){
        GameLauncher.log.info("Loading game from save");
    }

    public static void win(){
        hero.setStar_counter(0);
        gameLoop.stop();
        Scene winning_scene = new AreYouWinningSon();
        stage.setScene(new AreYouWinningSon());
        stage.show();
        GameLauncher.log.info("Player won!");
    }
    public static AContoller getCur_state() {
        return cur_state;
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
