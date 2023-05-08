package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.enteties.Star;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class LevelHandler extends AContoller {
    private static Logger log = Logger.getLogger(LevelHandler.class.getName());

    private static Stage cur_stage;

    private LevelView view;
    private Level level_config;
    private HashSet<KeyCode> pushed_keys = new HashSet<>();

    protected Player hero;
    private int redrawStarsCounter = 0;

    /**
     * basic functions
     */
    public LevelHandler(Player hero, Level level, Stage stage){
        log.info("Setting level handler...");
        //basic setting
        this.hero = hero;
        this.level_config = level;
        cur_stage = stage;

        view = new LevelView();
    }


    public void draw_level_start(){
        log.info("First draw of scene");
        view.draw_all(level_config.getMap(), hero, level_config.getStars());
        view.setOnKeyPressed(this::keyPressedHandler);
        view.setOnKeyReleased(this::keyReleasedHandler);

        cur_stage.setScene(view);
    }
    public void keyPressedHandler(KeyEvent e) {
        KeyCode code = e.getCode();
        if (!pushed_keys.contains(code)) {
            pushed_keys.add(code);
        }
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
        render();
        if(hero.getStar_counter() == 3)
        {
            GameLogic.win();
        }
    }

    public void render(){
        view.clearPlayer();
        view.drawCreature(hero);

        view.updateCamera(hero.getPosition().getX(), hero.getPosition().getY());
        redrawStarsCounter++;

        // Redraw the stars every REDRAW_STARS_INTERVAL ticks
        int REDRAW_STARS_INTERVAL = 10;
        if (redrawStarsCounter >= REDRAW_STARS_INTERVAL) {
            view.clearStar();
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
                view.clearStar();
                view.drawStar(level_config.getStars());
                log.info(Player.class.getName() + " picked up " + hero.getStar_counter() + " stars");
            }
        }
        //fight
        if(pushed_keys.contains(KeyCode.R))
        {
            System.out.println("Fight");

        }
        //inventory
        if(pushed_keys.contains(KeyCode.E))
        {
            log.info(Player.class.getName() + " opened inventory");

        }
        //in game menu
        if(pushed_keys.contains(KeyCode.ESCAPE))
        {
            log.info(Player.class.getName() + " in game menu");

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
