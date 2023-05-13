package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Enemy;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.level.Level;
import cz.cvut.anokhver.level.LevelView;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;


import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;

import static cz.cvut.anokhver.enteties.Movable.rangeCalculateCreatures;

public class LevelHandler extends AContoller {
    private static Stage cur_stage;

    private final LevelView view;
    private Level level_config;
    private final HashSet<KeyCode> pushed_keys = new HashSet<>();

    protected Player hero;
    private int redrawStarsCounter = 0;

    private int moveCounter = 10;
    private final int MOVE_INTERVAL = 20; // make the enemies move every 20 ticks

    /**
     * basic functions
     */
    public LevelHandler(Player hero, Level level, Stage stage){
        GameLauncher.log.info("Setting level handler...");
        //basic setting
        this.hero = hero;
        this.hero.setHealth(100);
        this.level_config = level;
        cur_stage = stage;

        view = new LevelView();
    }


    public void draw_level_start(){
        GameLauncher.log.info("First draw of scene");
        view.draw_all(level_config.getMap(), hero, level_config.getStars());
        view.setOnKeyPressed(this::keyPressedHandler);
        view.setOnKeyReleased(this::keyReleasedHandler);

        cur_stage.setScene(view);
    }
    public void keyPressedHandler(KeyEvent e) {
        KeyCode code = e.getCode();
        pushed_keys.add(code);
    }

    public void keyReleasedHandler(KeyEvent e) {
        KeyCode code = e.getCode();
        pushed_keys.remove(code);
    }

    /**
     * updating + rendering
     */
        public void update(double delta) {
            check_keys(delta);
            level_config.getEnemies().removeIf(enemy -> enemy.getHealth() <= 0);
            render();
            // increment the move counter
            moveCounter++;

            // if the move counter reaches the interval, make the enemies move randomly and reset the counter
            if (moveCounter >= MOVE_INTERVAL) {
                for (Enemy enemy : level_config.getEnemies()) {
                    enemy.setCurDir(enemy.generateDirection());
                }
                moveCounter = 0;
            }
            for (Enemy enemy : level_config.getEnemies()) {
                enemy.move(enemy.getCurDirection(),delta);
                enemy.cooldown-=1;
                damagePlayerIfInRange(enemy);
            }

            if(hero.getStar_counter() == 3)
            {
                GameLogic.win();
            }

            if (hero.getHealth() <= 0)
            {
                GameLogic.win();
            }


        }

    public void render(){
        view.clearCanvas(view.cur_canvases.get("player").getGraphicsContext2D());
        view.drawCreature(hero, view.cur_canvases.get("player").getGraphicsContext2D());

        view.clearCanvas(view.cur_canvases.get("enemies").getGraphicsContext2D());
        view.drawEnemies(level_config.getEnemies());
        view.updateCamera(hero.getPosition().getX(), hero.getPosition().getY());
        redrawStarsCounter++;

        // Redraw the stars every REDRAW_STARS_INTERVAL ticks
        int REDRAW_STARS_INTERVAL = 10;
        if (redrawStarsCounter >= REDRAW_STARS_INTERVAL) {
            view.clearCanvas(view.cur_canvases.get("star").getGraphicsContext2D());
            view.drawStar(level_config.getStars());
            redrawStarsCounter = 0;
        }
    }

    private void check_keys(double delta){

        //moving
        if (pushed_keys.contains(KeyCode.W))
        {
            hero.move(Direction.TOP, delta);
        }
        if (pushed_keys.contains(KeyCode.A))
        {
            hero.move(Direction.LEFT, delta);
        }
        if (pushed_keys.contains(KeyCode.S))
        {
            hero.move(Direction.BOTTOM, delta);

        }
        if (pushed_keys.contains(KeyCode.D))
        {
            hero.move(Direction.RIGHT, delta);
        }
        //objet interact
        if(pushed_keys.contains(KeyCode.SPACE))
        {
            List<Star> stars = level_config.getStars();
            int ind = hero.checkForStars(level_config.getStars());
            if(ind != -1)
            {
                stars.remove(ind);
                level_config.setStars(stars);
                hero.setStar_counter(hero.getStar_counter() + 1);
                view.clearCanvas(view.cur_canvases.get("star").getGraphicsContext2D());
                view.drawStar(level_config.getStars());
                GameLauncher.log.info(Player.class.getName() + " picked up " + hero.getStar_counter() + " stars");
            }
        }
        //fight
        if(pushed_keys.contains(KeyCode.R))
        {

            int ind_enemy = checkForEnemies(level_config.getEnemies(), hero);
            if(ind_enemy != -1)
            {
                Enemy cur_enemy = level_config.getEnemies().get(ind_enemy);
                cur_enemy.setHealth(cur_enemy.getHealth() - hero.getDamage());
                GameLauncher.log.info("Hero damaged enemy" + ind_enemy + " " + cur_enemy.getName());
            }

        }
        //inventory
        if(pushed_keys.contains(KeyCode.E))
        {
            GameLauncher.log.info(Player.class.getName() + " opened inventory");

        }
        //in game menu
        if(pushed_keys.contains(KeyCode.ESCAPE))
        {
            GameLauncher.log.info(Player.class.getName() + " in game menu");

        }
    }

    public int checkForEnemies(List<Enemy> enemies, Player player) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            double distance = rangeCalculateCreatures(player, enemy);
            if (distance <= hero.getDamage_radius() * Configuration.getTileSize()) {
                return i;
            }
        }
        return -1;
    }

    public void damagePlayerIfInRange(Enemy enemy) {
        double distance = rangeCalculateCreatures(hero, enemy);
        System.out.println(distance + "\n");
        if (distance <= enemy.getDamageRadius() * Configuration.getTileSize()/2 && enemy.cooldown <= 0 ) {
            hero.setHealth((float) (hero.getHealth() - enemy.getDamage()));
            GameLauncher.log.info("Player was damaged by " + enemy.getName() + " now health is: " + hero.getHealth());
            enemy.cooldown = enemy.getSpeedDamage();
        }
    }


    /**
     * GETTERS SETTERS
     */
    public Level getLevel(){
        return level_config;
    }

    public Coordinates getCharacterPosition() {
        return hero.getPosition();
    }

    @Override
    public VBox getView() {
        return null;
    }

    public static Stage getCur_stage() {
        return cur_stage;
    }

    public static void setCur_stage(Stage cur_stage) {
        LevelHandler.cur_stage = cur_stage;
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
