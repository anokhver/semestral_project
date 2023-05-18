package cz.cvut.anokhver.level;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.DelayedAction;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.contollers.AContoller;
import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.menu.AMenu;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.List;

import static cz.cvut.anokhver.enteties.Movable.rangeCalculateCreatures;
import static cz.cvut.anokhver.movement.Coordinates.minus;

/**
 * Handling all the level
 * saves in it the view and the level configurations
 * handling key presses
 * updating the game loop
 *
 * @author Veronika
 */
public class LevelHandler extends AContoller {
    private static Stage cur_stage;

    private final LevelView view;
    private final HashSet<KeyCode> pushed_keys = new HashSet<>();
    private final int MOVE_INTERVAL = 20; // make the enemies move every 20 ticks
    protected Player hero;
    private Level level_config;
    //some variables that help but could be done much better...
    private int moveCounter = 0;
    private int redrawStarsCounter = 0;
    private Boolean gameWon = false;

    /**
     * Passing all needed param to control the level
     *
     * @param hero
     * @param level
     * @param stage
     */
    public LevelHandler(Player hero, Level level, Stage stage) {
        GameLauncher.log.info("Setting level handler...");
        //basic setting
        this.hero = hero;
        this.level_config = level;
        cur_stage = stage;

        view = new LevelView();
    }

    /*===========================
     *Basic setting
     ===========================*/

    public static Stage getCur_stage() {
        return cur_stage;
    }

    public static void setCur_stage(Stage cur_stage) {
        LevelHandler.cur_stage = cur_stage;
    }

    /**
     * Draw level the first time
     */
    public void draw_level_start() {
        GameLauncher.log.info("First draw of scene");
        level_config.startTimer();
        view.draw_all(level_config.getMap(), level_config.getStars(), level_config.getRemainingTime());
        view.setOnKeyPressed(this::keyPressedHandler);
        view.setOnKeyReleased(this::keyReleasedHandler);

        cur_stage.setScene(view);
    }

    /*===========================
     *Updating & Rendering
     ===========================*/

    private void keyPressedHandler(KeyEvent e) {
        KeyCode code = e.getCode();
        pushed_keys.add(code);
    }

    private void keyReleasedHandler(KeyEvent e) {
        KeyCode code = e.getCode();
        pushed_keys.remove(code);
    }

    /**
     * Update game state each tick
     * damage player pick up stars
     * render new view
     *
     * @param delta
     */
    @Override
    public void update(double delta) {
        //clear killed enemies
        level_config.getEnemies().removeIf(enemy -> enemy.getHealth() <= 0);
        view.getHealthView().checkForChange(hero.getHealth());

        check_keys(delta);
        //change heart texture

        render();
        // if the move counter reaches the interval, make the enemies move randomly and reset the counter
        // increment the move counter for controlling random monsters movement
        moveCounter++;
        if (moveCounter >= MOVE_INTERVAL) {
            for (Enemy enemy : level_config.getEnemies()) {
                if (enemy != null) {
                    chasePlayerIfInRange(enemy);
                }
            }
            moveCounter = 0;
        }

        //damaging player if he is close enough
        for (Enemy enemy : level_config.getEnemies()) {
            if (enemy != null) {
                enemy.move(enemy.getCurDirection(), delta);
                enemy.cooldown -= 1;
                damagePlayerIfInRange(enemy);
            }
        }


        //win
        if (hero.getStar_counter() == Configuration.getCountStars()) {
            if (!gameWon) {
                GameLauncher.log.info("You win yepi :D");
                level_config.getEnemies().removeAll(level_config.getEnemies());
                level_config.stopTimer();
            }

            gameWon = true; // set gameWon to true
            new DelayedAction(Duration.millis(2000), GameLogic::win);
        }
        //lose :(
        if (hero.getHealth() <= 0 || level_config.getRemainingTime() == 0) {
            GameLauncher.log.info("You lose :(");
            GameLogic.lose();
        }


    }

    /*===========================
    *Checkers
    ===========================*/

    /**
     * Draw the game view
     */
    private void render() {
        view.updateCamera(hero.getPosition().getX(), hero.getPosition().getY());

        view.clearCanvas(view.cur_canvases.get("heroStats").getGraphicsContext2D());
        view.drawStats(level_config.getRemainingTime());

        view.clearCanvas(view.cur_canvases.get("player").getGraphicsContext2D());
        view.drawCreature(hero, view.cur_canvases.get("player").getGraphicsContext2D());

        view.clearCanvas(view.cur_canvases.get("enemies").getGraphicsContext2D());
        view.drawEnemies(level_config.getEnemies());

        redrawStarsCounter++;

        // Redraw the stars every REDRAW_STARS_INTERVAL ticks
        int REDRAW_STARS_INTERVAL = 10;
        if (redrawStarsCounter >= REDRAW_STARS_INTERVAL) {
            view.clearCanvas(view.cur_canvases.get("star").getGraphicsContext2D());
            view.drawStar(level_config.getStars());
            redrawStarsCounter = 0;
        }
    }

    private void check_keys(double delta) {

        //moving
        if (pushed_keys.contains(KeyCode.W)) {
            hero.move(Direction.TOP, delta);
            hero.setCurTextureDirection(Direction.TOP);
        }
        if (pushed_keys.contains(KeyCode.A)) {
            hero.move(Direction.LEFT, delta);
            hero.setCurTextureDirection(Direction.LEFT);

        }
        if (pushed_keys.contains(KeyCode.S)) {
            hero.move(Direction.BOTTOM, delta);
            hero.setCurTextureDirection(Direction.BOTTOM);

        }
        if (pushed_keys.contains(KeyCode.D)) {
            hero.move(Direction.RIGHT, delta);
            hero.setCurTextureDirection(Direction.RIGHT);

        }
        //objet interact
        if (pushed_keys.contains(KeyCode.SPACE)) {
            List<Star> stars = level_config.getStars();
            int ind = checkForStars(level_config.getStars());
            if (ind != -1) {
                stars.remove(ind);
                level_config.setStars(stars);
                hero.setStar_counter(hero.getStar_counter() + 1);
                view.clearCanvas(view.cur_canvases.get("star").getGraphicsContext2D());
                view.drawStar(level_config.getStars());
                GameLauncher.log.info(Player.class.getName() + " picked up " + hero.getStar_counter() + " stars");
            }
        }
        //fight
        if (pushed_keys.contains(KeyCode.R)) {

            int ind_enemy = checkForEnemies(level_config.getEnemies(), hero);
            if (ind_enemy != -1) {
                Enemy cur_enemy = level_config.getEnemies().get(ind_enemy);
                cur_enemy.setHealth(cur_enemy.getHealth() - hero.getDamage());
                GameLauncher.log.info("Hero damaged enemy" + ind_enemy + " " + cur_enemy.getName());
            }

        }
        //inventory
        if (pushed_keys.contains(KeyCode.E)) {
            pushed_keys.clear();
            GameLauncher.log.info(Player.class.getName() + " opened inventory");
            //stopping timer and game
            level_config.stopTimer();
            GameLogic.stopGame();

            GameLogic.setInventory();

        }
        //in game menu
        if (pushed_keys.contains(KeyCode.ESCAPE)) {
            pushed_keys.clear();
            //stopping timer and game
            level_config.stopTimer();
            GameLogic.stopGame();

            GameLogic.setInGameMenu();
        }
    }

    private int checkForEnemies(List<Enemy> enemies, Player player) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            double distance = rangeCalculateCreatures(player, enemy);
            if (distance <= hero.getDamage_radius() * Configuration.getTileSize()) {
                return i;
            }
        }
        return -1;
    }

    private int checkForStars(List<Star> stars) {
        int playerCenterX = (int) (GameLogic.getPlayer().getPosition().getX() + PlayerConfigurations.getTextureWidth() / 2.0);
        int playerCenterY = (int) (GameLogic.getPlayer().getPosition().getY() + PlayerConfigurations.getTextureHeight() / 2.0);

        for (int i = 0; i < stars.size(); i++) {
            Star star = stars.get(i);
            int starCenterX = (int) (star.getPosition().getX() + Configuration.getTileSize() / 2.0);
            int starCenterY = (int) (star.getPosition().getY() + Configuration.getTileSize() / 2.0);

            double distance = minus(new Coordinates(playerCenterX, playerCenterY), new Coordinates(starCenterX, starCenterY));
            if (distance <= Configuration.getPickUp()) {
                return i;
            }
        }
        return -1;
    }

    private void damagePlayerIfInRange(Enemy enemy) {
        double distance = rangeCalculateCreatures(hero, enemy);
        if (distance <= enemy.getDamageRadius() * Configuration.getTileSize() && enemy.cooldown <= 0) {
            hero.setHealth((hero.getHealth() - enemy.getDamage()));
            GameLauncher.log.info("Player was damaged by " + enemy.getName() + " now health is: " + hero.getHealth());
            enemy.cooldown = enemy.getSpeedDamage();
        }
    }

    private void chasePlayerIfInRange(Enemy enemy) {
        double distance = rangeCalculateCreatures(hero, enemy);
        if (distance <= enemy.getSeeRadius() * Configuration.getTileSize()) {
            enemy.setCurDir(calculateChaseDirection(enemy));
        } else {
            enemy.setCurDir(Coordinates.generateDirection());
        }
    }

    private Direction calculateChaseDirection(Enemy enemy) {
        int playerX = hero.getPosition().getX();
        int playerY = hero.getPosition().getY();
        int enemyX = enemy.getPosition().getX();
        int enemyY = enemy.getPosition().getY();

        int distanceX = Math.abs(playerX - enemyX);
        int distanceY = Math.abs(playerY - enemyY);

        if (distanceX > distanceY) {
            if (playerX < enemyX) {
                return Direction.LEFT;
            } else {
                return Direction.RIGHT;
            }
        } else {
            if (playerY < enemyY) {
                return Direction.TOP;
            } else {
                return Direction.BOTTOM;
            }
        }
    }

    /*===========================
      *Getters & Setters
     ===========================*/
    public Level getLevel() {
        return level_config;
    }

    public Coordinates getCharacterPosition() {
        return hero.getPosition();
    }

    @Override
    public AMenu getView() {
        return null;
    }

    public Level getLevel_config() {
        return level_config;
    }

    public void setLevel_config(Level level_config) {
        this.level_config = level_config;
    }

    public Player getHero() {
        return hero;
    }

    public void setHero(Player hero) {
        this.hero = hero;
    }


}
