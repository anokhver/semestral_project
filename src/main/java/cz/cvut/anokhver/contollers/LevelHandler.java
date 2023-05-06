package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.enteties.Player;
import cz.cvut.anokhver.movement.Coordinates;
import cz.cvut.anokhver.movement.Direction;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashSet;

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
        check_player(delta);
        render();

    }

    public void render(){
        view.clearPlayer();
        view.drawCreature(hero);

        view.updateCamera(hero.getPosition().getX(), hero.getPosition().getY());
    }

    private void check_player(double delta){
        if (pushed_keys.contains("W"))
        {
            hero.move(Direction.TOP, delta);
        }
        if (pushed_keys.contains("A"))
        {
            hero.move(Direction.LEFT, delta);
        }
        if (pushed_keys.contains("S"))
        {
            hero.move(Direction.BOTTOM, delta);

        }
        if (pushed_keys.contains("D"))
        {
            hero.move(Direction.RIGHT, delta);
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
