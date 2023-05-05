package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;

import static cz.cvut.anokhver.additional.FileManagement.create_proper_path;
import static java.lang.Thread.sleep;

public class LevelHandler extends AContoller {

    private static Stage cur_stage;

    private final LevelView view;
    private final Level level_config;
    private HashSet<String> pushed_keys = new HashSet<>();

    protected final Player hero;

    /**
     * basic functions
     */
    public LevelHandler(Player hero, Level level, Stage stage){
        //basic setting
        this.hero = hero;
        this.level_config = level;
        cur_stage = stage;

        view = new LevelView();
    }

    public void draw_level_start(){

        view.draw_all(level_config.getMap(), hero);
        cur_stage.setScene(view);

        //setting the key managing
        view.setOnKeyPressed(this::keyPressedHandler);
        view.setOnKeyReleased(this::keyReleasedHandler);

        // Show the Stage
        cur_stage.show();
    }
    public void keyPressedHandler(KeyEvent e) {
        String code = e.getCode().toString();

        if (!pushed_keys.contains(code)) {
            pushed_keys.add(code);
        }
    }

    public void keyReleasedHandler(KeyEvent e) {
        String code = e.getCode().toString();
        pushed_keys.remove(code);
    }

    /**
     * updating + rendering
     */
    public void update(double delta) {

        if (pushed_keys.contains("W"))
        {
            move_hero(Direction.TOP, delta);


        }
        if (pushed_keys.contains("A"))
        {
            move_hero(Direction.LEFT, delta);


        }
        if (pushed_keys.contains("S"))
        {
            move_hero(Direction.BOTTOM, delta);

        }
        if (pushed_keys.contains("D"))
        {
            move_hero(Direction.RIGHT, delta);
        }

        render();

    }

    public void render(){
        view.clearPlayer();
        view.drawPlayer(hero);
    }
    /**
     * moving player
     */
    public void move_hero(Direction direction, double delta) {
        Configuration.init(create_proper_path("config.json"));


        float speed = (float) (hero.getWalk_speed() * delta);
        int x = hero.getPosition().getX();
        int y = hero.getPosition().getY();
        System.out.println("HI!! " + x +" "+ y + speed);

        // Update the player's position based on the given direction
        switch (direction) {
            case TOP:
                y -= speed;
                break;
            case BOTTOM:
                y += speed*2;
                break;
            case RIGHT:
                x += speed*2;
                break;
            case LEFT:
                x -= speed;
                break;
            default:
                break;
        }

        // Update the player's position if it's within the game world boundaries
        if ((x >= 0 || x <= Configuration.getMapWidth() - hero.getTexture().getWidth())
                && (y >= 0 || y <= Configuration.getMapHeight() - hero.getTexture().getHeight()))
        {
            System.out.println("MOVED!! " + x + y);
            hero.setPosition(new Coordinates(x, y));
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

}
