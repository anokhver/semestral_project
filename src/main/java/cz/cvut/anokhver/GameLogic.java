package cz.cvut.anokhver;

import cz.cvut.anokhver.contollers.AContoller;
import cz.cvut.anokhver.contollers.GameMenuController;
import cz.cvut.anokhver.contollers.InventoryController;
import cz.cvut.anokhver.level.Level;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.level.LevelHandler;
import cz.cvut.anokhver.menu.AreYouWinningSon;
import cz.cvut.anokhver.movement.Coordinates;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.Dictionary;
import java.util.Hashtable;


public class GameLogic {

    /**
     * STAGE + MENU
     */
    private static Stage stage;
    private static final Dictionary<String, AContoller> controllers = new Hashtable<>();

    /**
     * THE GAME PARAMETERS
     */
    private static AContoller cur_state;

    protected static LevelHandler cur_level;
    private static final Player hero = new Player();
    private static final GameLoop gameLoop = new GameLoop();

    public GameLogic(Stage primaryStage){
        GameLauncher.log.info("Setting up the logic");
        hero.setInventory(new InventoryController());

        GameLogic.stage = primaryStage;
        //log.info("Setting coordinates to player 100, 100");
        hero.setPosition(new Coordinates(100,100));

        controllers.put("MainMenu", new MainMenuController());
        controllers.put("InGameMenu", new GameMenuController());
        controllers.put("Inventory", hero.getInventory());
        cur_state = controllers.get("MainMenu");
        setMainMenu();

        stage.show();
    }

    public static void setMainMenu(){
        stage.setScene(null);
        cur_state = controllers.get("MainMenu");
        stage.setScene(cur_state.getView().getScene());
        GameLauncher.log.info("Open main menu");
    }

    public static void setInGameMenu(){
        cur_state = controllers.get("InGameMenu");
        stage.setScene(cur_state.getView().getScene());
        GameLauncher.log.info("Open in game menu");
        stage.show();
    }

    public static void setInventory(){
        cur_state = controllers.get("Inventory");
        cur_state.getView().update_menu();
        stage.setScene(cur_state.getView().getScene());

        stage.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.E) {
                renewGame();
            }
        });

        stage.show();
    }

    public static void new_game(int id){
        GameLauncher.log.info("Start new game");
        stage.setScene(null);

        //creating and drawing
        cur_level = new LevelHandler(hero, new Level(id), stage);
        controllers.put("CurLevel", cur_level);

        cur_level.draw_level_start();
        //starting game loop
        startGame();
    }

    public static void renewGame(){
        GameLauncher.log.info("Renew new game");
        stage.setScene(null);
        cur_level.draw_level_start();
        startGame();
    }
    public static void load_game(){
        GameLauncher.log.info("Loading game from save");
    }

    public static void win(){
        cur_level = null;
        hero.setStar_counter(0);
        stopGame();
        stage.setScene(new AreYouWinningSon());
    }

    public static void lose(){
        cur_level = null;
        hero.setStar_counter(0);
        stopGame();
        stage.setScene(new AreYouWinningSon());
    }

    public static void stopGame() {
        gameLoop.stop();
    }

    public static void startGame() {
        gameLoop.start();
    }

    public static Player getPlayer(){return hero;}
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
