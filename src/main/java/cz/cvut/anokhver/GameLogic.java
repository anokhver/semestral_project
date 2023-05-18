package cz.cvut.anokhver;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.DelayedAction;
import cz.cvut.anokhver.additional.FileManagement;
import cz.cvut.anokhver.additional.SavingLoading;
import cz.cvut.anokhver.contollers.AContoller;
import cz.cvut.anokhver.contollers.GameMenuController;
import cz.cvut.anokhver.contollers.MainMenuController;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.level.Level;
import cz.cvut.anokhver.level.LevelHandler;
import cz.cvut.anokhver.menu.AreYouWinningSon;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * handling all game logic
 * setting correct views
 * changing menus
 * staring game etc...
 *
 * @author Veronika
 */
public class GameLogic {

    private static final Dictionary<String, AContoller> controllers = new Hashtable<>();
    private static final GameLoop gameLoop = new GameLoop();
    protected static LevelHandler cur_level;
    /**
     * STAGE + MENU
     */
    private static Stage stage;
    /**
     * THE GAME PARAMETERS
     */
    private static AContoller cur_state;
    private static Player hero;

    public GameLogic(Stage primaryStage) {
        GameLauncher.log.info("Setting up the logic");
        GameLogic.stage = primaryStage;

        controllers.put("MainMenu", new MainMenuController());
        controllers.put("InGameMenu", new GameMenuController());
        cur_state = controllers.get("MainMenu");
        setMainMenu();

        stage.show();
    }

    /*===========================
    *Menu setters
    ===========================*/

    /**
     * Setting the main menu as a view of Application
     * the view is taken from controllers array
     */
    public static void setMainMenu() {
        stage.setScene(null);

        cur_state = controllers.get("MainMenu");
        stage.setScene(cur_state.getView().getScene());
        GameLauncher.log.info("Open main menu");
    }


    /**
     * Setting the in game menu as a view of Application
     * the view is taken from controllers array
     */
    public static void setInGameMenu() {
        stage.setScene(null);

        cur_state = controllers.get("InGameMenu");
        stage.setScene(cur_state.getView().getScene());
        GameLauncher.log.info("Open in game menu");
    }

    /**
     * Setting the inventory as a view of Application
     * the view is taken from controllers array
     */
    public static void setInventory() {
        stage.setScene(null);

        cur_state = controllers.get("Inventory");
        cur_state.getView().update_menu();
        stage.setScene(cur_state.getView().getScene());
        GameLauncher.log.info("Open inventory menu");
    }

    /*===========================
    *Game load save new
    ===========================*/

    /**
     * Creates a new game based on the level id
     *
     * @param id the id of level that will be created (every level is coded with level"id")
     */
    public static void new_game(int id) {
        GameLauncher.log.info("Start new game");
        stage.setScene(null);

        hero = new Player();
        controllers.put("Inventory", hero.getInventory());

        //creating and drawing
        cur_level = new LevelHandler(hero, new Level(id), stage);
        controllers.put("CurLevel", cur_level);

        cur_level.draw_level_start();
        //starting game loop
        startGame();
    }

    /**
     * Renew current game session
     */
    public static void renewGame() {
        GameLauncher.log.info("Renew game");
        stage.setScene(null);

        if (cur_level != null) cur_level.draw_level_start();
        else new_game(1);

        startGame();
    }

    /**
     * Loading game session from Json files (loading all level and player)
     */
    public static void load_game() {
        GameLauncher.log.info("Loading game from save");
        stage.setScene(null);

        //loading the hero if the save was not found creating default player
        hero = SavingLoading.loadFromJsonPlayer(FileManagement.createProperPath("saves/player.json"));
        if (hero == null) {
            hero = new Player();
        }
        controllers.put("Inventory", hero.getInventory());

        //loading full level if not found write on the screen and come back to the main menu
        Level level_con = SavingLoading.loadFromJsonLevel(FileManagement.createProperPath("saves/level.json"));
        if (level_con == null) {
            GameLauncher.log.info("Could not find a save");

            Label messageLabel = new Label("Could not find a save");
            StackPane root = new StackPane(messageLabel);
            stage.setScene(new Scene(root, Configuration.getWindowWidth(), Configuration.getWindowHeight()));

            new DelayedAction(Duration.millis(3000), GameLogic::setMainMenu);
            return;
        }

        //setting curent level
        cur_level = new LevelHandler(hero, level_con, stage);
        controllers.put("CurLevel", cur_level);

        cur_level.draw_level_start();

        //starting game loop
        startGame();
    }

    /**
     * Saving current session to Json file (level & player)
     */
    public static void saveGame() {
        GameLauncher.log.info("Saving current session");
        SavingLoading.saveToJsonLevel(FileManagement.createProperPath("saves/level.json"), cur_level.getLevel_config());
        SavingLoading.saveToJsonPlayer(FileManagement.createProperPath("saves/player.json"), hero);
    }

    /*===========================
    *Win & lose/ Stop & start
    ===========================*/

    /**
     * Setting the win screen and on the button push coming back to the menu
     * + resetting cur_level
     */
    public static void win() {
        stopGame();
        cur_level = null;
        hero.setStar_counter(0);
        stage.setScene(null);
        Scene cur_scene = new AreYouWinningSon("win");
        stage.setScene(cur_scene);
        stage.show();

    }

    /**
     * Setting the lose screen and on the button push coming back to the menu
     * + resetting cur_level
     */
    public static void lose() {
        stopGame();
        cur_level = null;
        hero.setStar_counter(0);
        stage.setScene(null);
        Scene cur_scene = new AreYouWinningSon("lose");
        stage.setScene(cur_scene);
        stage.show();
    }

    /**
     * Stop game loop
     */
    public static void stopGame() {
        gameLoop.stop();
    }

    /**
     * Start game loop
     */
    public static void startGame() {
        gameLoop.start();
    }

    public static Player getPlayer() {
        return hero;
    }
}


class GameLoop extends AnimationTimer {
    private long lastNanoTime = System.nanoTime();

    @Override
    public void handle(long now) {
        double delta = (now - lastNanoTime) / 1000000000.0;

        // Frame rate cap
        if (delta > 1 / 1000.00) {
            lastNanoTime = now;
            GameLogic.cur_level.update(delta);
        }
    }


}

